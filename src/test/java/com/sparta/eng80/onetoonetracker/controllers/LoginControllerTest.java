package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.services.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void loadsContext() {
        Assertions.assertNotNull(loginController);
    }
}
