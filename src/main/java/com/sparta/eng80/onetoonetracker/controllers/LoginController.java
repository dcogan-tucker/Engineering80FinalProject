package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login() {
        if(securityService.isAuthenticated()){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String home() {
        return "index";
    }
}
