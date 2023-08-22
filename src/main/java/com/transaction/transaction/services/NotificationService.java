package com.transaction.transaction.services;

import com.transaction.transaction.domain.user.User;
import com.transaction.transaction.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message){
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);

    }System.out.println("notificação enviada para o usuário");
}
