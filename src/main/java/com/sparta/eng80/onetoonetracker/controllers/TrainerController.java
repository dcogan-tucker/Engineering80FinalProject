package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.TraineeService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import com.sparta.eng80.onetoonetracker.utilities.NewUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class TrainerController {

    private final TrainerService trainerService;
    private final GroupService groupService;
    private final TraineeService traineeService;

    public TrainerController(TrainerService trainerService, GroupService groupService, TraineeService traineeService) {
        this.trainerService = trainerService;
        this.groupService = groupService;
        this.traineeService = traineeService;
    }

    @PostMapping("/trainer/addTrainee")
    public ModelMap addNewUser(@ModelAttribute NewUserForm newUserForm, ModelMap modelMap) {
        modelMap.addAttribute("newUserForm", new NewUserForm());
        modelMap.addAttribute("allGroups", groupService.findAll());

        trainerService.addNewTrainee(
                groupService.findById(newUserForm.getGroupId()).get(),
                newUserForm.getFirstName(),
                newUserForm.getLastName(),
                "ROLE_TRAINEE"
        );

        return modelMap;
    }


    public Iterable<TraineeEntity> getTrainees(TrainerEntity trainer) {
        Iterable<TraineeEntity> trainees = traineeService.findByGroupId(trainer.getGroup().getGroupId());
        return trainees;
    }
}