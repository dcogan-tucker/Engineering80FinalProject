package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.security.PasswordEncryptor;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordChangerController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public PasswordChangerController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        UserEntity user = securityService.getCurrentUser();
        return "change_password";
    }

    @PostMapping("/change-password")
    public String setPassword(
                            @RequestParam("currentPassword") String currentPassword,
                            @RequestParam("newPassword") String newPassword,
                            @RequestParam("confirmPassword") String confirmPassword) {
        UserEntity userEntity = securityService.getCurrentUser();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches(userEntity.getPassword(), currentPassword));
        System.out.println(newPassword.equals(confirmPassword));
        System.out.println("P: " + currentPassword);
        System.out.println("NP: " + newPassword);
        System.out.println("CP: " + confirmPassword);
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error");
            return "redirect:/change-password";
        } else {
            System.out.println("Changed");
            return "index";
//            userEntity.setPassword(newPassword);
//            userEntity.setPasswordChanged(true);
//            userService.save(userEntity);
        }
    }
}
