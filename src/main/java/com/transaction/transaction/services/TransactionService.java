package com.transaction.transaction.services;

import com.transaction.transaction.TransactionApplication;
import com.transaction.transaction.domain.transaction.Transaction;
import com.transaction.transaction.dtos.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate;

    @Autowired
    private TransactionApplication repository;

    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User comprador = this.userService.findUserById(transaction.comprador_Id());
        User vendedor = this.userService.findUserById(transaction.vendedor_Id());

        userService.validateTransaction(comprador,transaction.value());

        boolean isAuthorized = this.authorizeTransaction(comprador,transaction.value());
        if(!isAuthorized) {
            throw new Exception("transação não autorizada");

            Transaction newTransaction = new Transaction();
            newTransaction.setAmount(transaction.value());
            newTransaction.setComprador(comprador);
            newTransaction.setVendedor(vendedor);
            newTransaction.setTimestamp(LocalDateTime.now());

            comprador.setBalance(comprador.getBalance().subtract(transaction.value()));
            vendedor.setBalance(vendedor.getBalance().add(transaction.value()));

            this.repository.save(newTransaction);
            this.userService.saveUser(comprador);
            this.userService.saveUser(vendedor);
            this.notificationService.sendNotification(comprador,"transação efetuada com sucesso");
            this.notificationService.sendNotification(vendedor,"transação efetuada com sucesso");


        }

    }
    public boolean authorizeTransaction(User comprador, BigDecimal value){
        ResponseEntity<Map>authorizationResponse = restTemplate.getForEntity("url",Map.class);
        if(authorizationResponse.getStatusCode()== HttpStatus.OK){
            String message = (String)authorizationResponse.getBody().get("message");
            return "autorizado".equalsIgnoreCase(message);

        }else return false;
    }
}
