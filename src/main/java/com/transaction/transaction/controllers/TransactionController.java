package com.transaction.transaction.controllers;

import com.transaction.transaction.domain.transaction.Transaction;
import com.transaction.transaction.dtos.TransactionDTO;
import com.transaction.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    private ResponseEntity<transaction> createTransaction(@ResponseBody TransactionDTO transaction)throws Exception{
        Transaction newTransaction=this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

}
