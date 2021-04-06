package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private UserService userService;
    private SecurityService securityService;

    public MainController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping("/competencies")
    public String competencies() {
        return "/fragments/competencies";
    }

    @RequestMapping("/profile")
    public String viewProfile(ModelMap model){
        UserEntity user = securityService.getCurrentUser();
        if(user.getRole().equals("ROLE_TRAINEE")){
            model.addAttribute("name", user.getTrainee().getFirstName() + " " + user.getTrainee().getLastName());
            FeedbackEntity feedback = user.getTrainee().getFeedbacks().iterator().next();
            model.addAttribute("feedback", feedback);
        } else if(user.getRole().equals("ROLE_TRAINER")){
            model.addAttribute("name", user.getTrainer().getFirstName() + " " + user.getTrainer().getLastName());
        } else {
            model.addAttribute("name", user.getRole());
        }
        model.addAttribute("user", user);

        return "/fragments/profile";
    }
}
