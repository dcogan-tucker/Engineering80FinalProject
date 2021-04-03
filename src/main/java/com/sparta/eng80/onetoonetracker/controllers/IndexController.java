package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final TrainerController trainerController;
    private final GroupService groupService;
    private final StreamService streamService;
    private final TrainerService trainerService;

    @Autowired
    public IndexController(SecurityService securityService, TrainerController trainerController, GroupService groupService, StreamService streamService, TrainerService trainerService) {
        this.securityService = securityService;
        this.trainerController = trainerController;
        this.groupService = groupService;
        this.streamService = streamService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String method(ModelMap model) {
        if(securityService.isAuthenticated()){
            switch (securityService.getCurrentUser().getRole()) {
                case "ROLE_TRAINER":
                    model.addAttribute("allGroups", groupService.findAll());
                    model.addAttribute("allStreams", streamService.findAll());
                    model.addAttribute("allTrainers", trainerService.findAll());
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
