package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final SecurityService securityService;
    private final TrainerController trainerController;
    private final TrainerService trainerService;

    @Autowired
    public LoginController(SecurityService securityService, TrainerController trainerController, TrainerService trainerService) {
        this.securityService = securityService;
        this.trainerController = trainerController;
        this.trainerService = trainerService;
    }

    @GetMapping("/login")
    public String login(ModelMap model) {
        if(securityService.isAuthenticated()){
            switch (securityService.getCurrentUser().getRole()) {
                case "ROLE_TRAINER":
                    Iterable<TraineeEntity> trainees =trainerController.getTrainees(securityService.getCurrentUser().getTrainer());
                    model.addAttribute("traineesInTrainerGroup", trainees);
                    break;
                case "ROLE_ADMIN":
                case "ROLE_TRAINEE":
                    break;
            }
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public String home() {
        return "index";
    }
}
