package com.prc.springbootwebsocket.controller;

import com.prc.springbootwebsocket.pojo.Chat;
import com.prc.springbootwebsocket.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GreetingController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /*
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting1(Message message){
        return message;
    }
    */

    @MessageMapping("/greeting")
    public void greeting(Message message){
        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }

    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
        chat.setFrom(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(chat.getFrom(),"/queue/chat",chat);
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }
}
