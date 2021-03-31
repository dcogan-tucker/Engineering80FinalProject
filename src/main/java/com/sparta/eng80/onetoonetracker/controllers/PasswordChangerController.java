package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        //Get Currently logged in user
        UserEntity user = userService.findByEmail(email).get();
        model.addAttribute("user", user);
        //Change to name of password changer page
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public void setPassword(@ModelAttribute("user") UserEntity userEntity, @RequestParam("password") String password) {
        userEntity.setPassword(password);
        userService.save(userEntity);
    }
}
