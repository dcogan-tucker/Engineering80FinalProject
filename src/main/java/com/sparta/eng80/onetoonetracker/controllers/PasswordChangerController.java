package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordChangerController {

    UserService userService;

    @Autowired
    public PasswordChangerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam("email") String email, Model model) {
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        //Change to name of password changer page
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public void setPassword(@RequestParam("password") String password) {
        //Change to get current user
        UserEntity user = new UserEntity();

        user.setPassword(password);
        userService.save(user);
    }
}
