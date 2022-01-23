package com.example.socketdemo.service;

import com.example.socketdemo.model.User;
import com.example.socketdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class WebSocketService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    //will send the number of users to subscribers of /topic/usersCount
    public void propagateUsersCount() {
        List<User> users = userRepository.findAll();
        simpMessagingTemplate.convertAndSend("/topic/usersCount", users.size());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        startUpdatingUsersCountEverySecond();
//    }
//
//    private void startUpdatingUsersCountEverySecond() {
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                List<User> users = userRepository.findAll();
//                System.out.println("sending" + new Date());
//                simpMessagingTemplate.convertAndSend("/topic/usersCount", users.size());
//            }
//        }, 0, 1000);
//    }
}
