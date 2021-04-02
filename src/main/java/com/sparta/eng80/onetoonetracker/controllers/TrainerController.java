package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.StreamService;
import com.sparta.eng80.onetoonetracker.services.TraineeService;
import com.sparta.eng80.onetoonetracker.services.TrainerService;
import com.sparta.eng80.onetoonetracker.utilities.NewGroupForm;
import com.sparta.eng80.onetoonetracker.utilities.NewUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrainerController {

    private final TrainerService trainerService;
    private final GroupService groupService;
    private final TraineeService traineeService;
    private final StreamService streamService;

    public TrainerController(TrainerService trainerService, GroupService groupService, TraineeService traineeService, StreamService streamService) {
        this.trainerService = trainerService;
        this.groupService = groupService;
        this.traineeService = traineeService;
        this.streamService = streamService;
    }

    @GetMapping("/trainer/addTrainee")
    public String main(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        model.addAttribute("allGroups", groupService.findAll());
        return "redirect:/";
    }

    @PostMapping("/trainer/addTrainee")
    public Model addNewUser(NewUserForm newUserForm, Model model) {
        trainerService.addNewTrainee(
                groupService.findById(newUserForm.getGroupId()).get(),
                newUserForm.getFirstName(),
                newUserForm.getLastName(),
                "ROLE_TRAINEE"
        );
        return model;
    }

    @GetMapping("/trainer/newGroup")
    public String newGroup(Model model) {
        model.addAttribute("newGroupForm", new NewGroupForm());
        model.addAttribute("allStreams", streamService.findAll());
        model.addAttribute("allTrainers", trainerService.findAll());
        return "redirect:/";
    }

    @PostMapping("/trainer/newGroup")
    public Model addNewUser(NewGroupForm newGroupForm, Model model) {
        groupService.addNewGroup(newGroupForm);
        return model;
    }


    public Iterable<TraineeEntity> getTrainees(TrainerEntity trainer) {
        Iterable<TraineeEntity> trainees = traineeService.findByGroupId(trainer.getGroup().getGroupId());
        return trainees;
    }
}