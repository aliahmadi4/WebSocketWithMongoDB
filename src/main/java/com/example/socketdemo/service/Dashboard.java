package com.example.socketdemo.service;

import com.example.socketdemo.model.User;
import com.example.socketdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class Dashboard {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    private void send() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<User> users = userRepository.findAll();
                System.out.println("sending" + new Date());
                simpMessagingTemplate.convertAndSend("/topic/dashboard", users.size());
            }
        }, 0, 1000);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        send();
    }
}
