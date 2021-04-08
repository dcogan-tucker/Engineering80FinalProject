package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageGroupController {

    private final SecurityService securityService;
    private final GroupService groupService;
    private final TraineeService traineeService;
    private final StreamService streamService;
    private final TrainerService trainerService;

    @Autowired
    public ManageGroupController(SecurityService securityService, GroupService groupService, TraineeService traineeService, StreamService streamService, TrainerService trainerService) {
        this.securityService = securityService;
        this.groupService = groupService;
        this.traineeService = traineeService;
        this.streamService = streamService;
        this.trainerService = trainerService;
    }

    @GetMapping("/group")
    public String manageGroup(Model model){
        UserEntity user = securityService.getCurrentUser();
        Iterable<TraineeEntity> trainees = traineeService.findByGroupId(user.getTrainer().getGroup().getGroupId());

        model.addAttribute("trainees", trainees);
        model.addAttribute("user", user);
        model.addAttribute("allGroups", groupService.findAll());
        model.addAttribute("allStreams", streamService.findAll());
        model.addAttribute("allUnassignedTrainers", trainerService.findAllUnassigned());

        return "fragments/trainer/manageGroup";
    }
}
