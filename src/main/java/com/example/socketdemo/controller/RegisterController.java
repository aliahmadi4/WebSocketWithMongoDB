package com.example.socketdemo.controller;

import com.example.socketdemo.model.*;
import com.example.socketdemo.repository.UserRepository;
import com.example.socketdemo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WebSocketService webSocketService;

    @GetMapping
    public String getNewUserPage(User user, Model model){
        model.addAttribute(user);
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute User user){
        userRepository.save(user);
        webSocketService.propagateUsersCount();
        return "redirect:/register";
    }
}
