package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final TrainerController trainerController;

    @Autowired
    public IndexController(SecurityService securityService, TrainerController trainerController) {
        this.securityService = securityService;
        this.trainerController = trainerController;
    }

    @GetMapping("/")
    public String method(ModelMap model) {
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
}
