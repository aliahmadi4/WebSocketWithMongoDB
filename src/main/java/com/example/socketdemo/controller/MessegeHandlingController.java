package com.example.socketdemo.controller;

import com.example.socketdemo.model.Message;
import com.example.socketdemo.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessegeHandlingController {

    //any messages comes from /app/chat will be redirected to subscribers of /topic/messages
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
