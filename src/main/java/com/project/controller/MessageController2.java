package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.DsMessage;
import com.project.entities.Message;
import com.project.entities.Questions;
import com.project.entities.User;
import com.project.repo.MessageRepository;
import com.project.repo.MessageRepository2;
import com.project.repo.UserRepository;

@RestController
public class MessageController2 {

    private final MessageRepository2 messageRepository;

    @Autowired
    public MessageController2(MessageRepository2 messageRepository) {
        this.messageRepository = messageRepository;
    }
    @Autowired
    private UserRepository userRepository;
    
    

    @MessageMapping("/message2")
    @SendTo("/topic/return-to2")
    public DsMessage getContent(DsMessage message) {
       // try {
         //   Thread.sleep(1000);
       // } catch (InterruptedException e) {
         //   e.printStackTrace();
       // }
    
        DsMessage messageEntity = new DsMessage(message.getName(), message.getContent());
        messageRepository.save(messageEntity);

        return message;
    }
    
   
    
 
  
    
  
}
