package com.transaction.transaction.services;

import com.transaction.transaction.domain.users.User;
import com.transaction.transaction.domain.users.UserType;
import com.transaction.transaction.dtos.UserDTO;
import com.transaction.transaction.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User comprador, BigDecimal amount)throws Exception{
        if(comprador.getUserType()== UserType.COMPRADOR){
            throw new Exception("Usuario nao esta autorizado a realizar este tipo de operação");
        }
        if(comprador.getBalance().compareTo(amount)<0){
            throw new Exception("saldo insuficiente");
        }
    }
    public User findUserById(Long id)throws Exception{
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("usuario nao encontrado"));
    }
    public User createUser(UserDTO data){
        User newUser=new User(data);
        this.saveUser(newUser);
        return newUser;
    }
    public List<User>getAllUsers(){return this.repository.findAll();
    }
    public  void saveUser(User user){this.repository.save(user);
    }
}
