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
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        PasswordEncoder passwordEncoder = passwordEncryptor.getBCryptPasswordEncoder();

        System.out.println("P: " + currentPassword);
        System.out.println("NP: " + newPassword);
        System.out.println("CP: " + confirmPassword);

        if (!passwordEncoder.matches(currentPassword, userEntity.getPassword()) || !newPassword.equals(confirmPassword)) {
            return "redirect:/change-password";
        } else {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userEntity.setPasswordChanged(true);
            userService.save(userEntity);
            return "redirect:/";

        }
    }
}
