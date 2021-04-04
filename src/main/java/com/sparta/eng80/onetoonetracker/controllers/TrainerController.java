package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.utilities.NewGroupForm;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.TraineeService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/addTrainee")
    public String addNewTrainee(@RequestParam Integer groupId, @RequestParam String firstName, @RequestParam String lastName, Model model) {
        trainerService.addNewTrainee(
                groupService.findById(groupId).get(),
                firstName,
                lastName,
                "ROLE_TRAINEE"
        );
        return "redirect:/";
    }

    @PostMapping("/addGroup")
    public String addNewGroup(
            @RequestParam String groupName,
            @RequestParam Date startDate,
            @RequestParam Integer streamId,
            @RequestParam Integer trainerId,
            Model model) {

        NewGroupForm newGroupForm = new NewGroupForm();
        newGroupForm.setGroupName(groupName);
        newGroupForm.setStartDate(startDate);
        newGroupForm.setStreamId(streamId);
        newGroupForm.setTrainerId(trainerId);

        groupService.addNewGroup(newGroupForm);
        return "redirect:/";
    }


    public Iterable<TraineeEntity> getTrainees(TrainerEntity trainer) {
        Iterable<TraineeEntity> trainees = traineeService.findByGroupId(trainer.getGroup().getGroupId());
        return trainees;
    }
}