package com.example.socketdemo.controller;

import com.example.socketdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ModelAndView getDashboardPage(ModelAndView modelAndView) {
        int usersCount = userRepository.findAll().size();
        modelAndView.addObject("usersCount", usersCount);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
