package com.example.socketdemo.controller;

import com.example.socketdemo.model.*;
import com.example.socketdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/msg")
    public String showHomePage() {
        userRepository.save(new User("John", 33));
        return "index";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @RequestMapping("/send")
    public String sendit() {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        //sending message to client side
        simpMessagingTemplate.convertAndSend("/topic/messages", new OutputMessage("Ali", "Hi", time));
        return "index";
    }

    @RequestMapping("/dashboard")
    public ModelAndView dash(ModelAndView modelAndView) {
//        List<User> users = userRepository.findAll();
//        modelAndView.addObject("users", users);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showNewUserpage(User user, Model model){
        model.addAttribute(user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user){
        userRepository.save(user);
        System.out.println(user);
        return "redirect:/register";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "layout/master";
    }


}
