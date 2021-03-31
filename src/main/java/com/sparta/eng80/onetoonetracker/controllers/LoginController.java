package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.services.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String login() {
        if(securityService.isAuthenticated()){
            return "redirect:/";
        }
        return "login";
    }
}
