package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.TraineeService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TrainerController {

    private final TrainerService trainerService;
    private final TraineeService traineeService;

    public TrainerController(TrainerService trainerService, TraineeService traineeService) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
    }


    public Iterable<TraineeEntity> getTrainees(TrainerEntity trainer) {
        Iterable<TraineeEntity> trainees = traineeService.findByGroupId(trainer.getGroup().getGroupId());
//        model.addAttribute("traineesInTrainerGroup", trainees);
        return trainees;
    }
}