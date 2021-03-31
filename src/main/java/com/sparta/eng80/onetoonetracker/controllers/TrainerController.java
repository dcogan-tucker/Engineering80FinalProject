package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService, GroupService groupService) {
        this.trainerService = trainerService;
    }

    @GetMapping("trainer/{id}")
    public ModelMap getTrainees(ModelMap model, int id) {
        Optional<TrainerEntity> trainer = trainerService.findById(id);
        if (trainer.isEmpty()) {
            //trainer id isn't valid, return empty list
            model.addAttribute("traineesInTrainerGroup", new ArrayList<>());
        } else {
            GroupEntity groupEntity = trainer.get().getGroup();
            Iterable<TraineeEntity> trainees = trainerService.getAllTraineesFromGroup(groupEntity);
        }

        model.addAttribute("traineesInTrainerGroup", trainees);
        return model;
    }
}