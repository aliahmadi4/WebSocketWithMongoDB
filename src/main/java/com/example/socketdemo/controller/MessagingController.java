package com.example.socketdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/msg")
public class MessagingController {
    @GetMapping
    public String getMessagingPage() {
        return "index";
    }
}
