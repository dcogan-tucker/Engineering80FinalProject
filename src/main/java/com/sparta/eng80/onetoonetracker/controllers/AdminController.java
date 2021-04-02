package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.AdminService;
import com.sparta.eng80.onetoonetracker.services.GroupService;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * This Controller is responsible for handling all the Admin controls.
 * Such as add and delete a Trainer, view all trainers, and general management.
 */
@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final GroupService groupService;

    public AdminController(AdminService adminService, UserService userService, GroupService groupService) {
        this.adminService = adminService;
        this.userService = userService;
        this.groupService = groupService;
    }

    //TODO Correct the return strings

    @GetMapping("/trainers")
    public String getAllTrainers(ModelMap modelMap){
        Iterable<TrainerEntity> trainers = adminService.findAllTrainers();
        modelMap.addAttribute("trainers", trainers);
        return "/fragments/trainers";
    }

    @GetMapping("/trainers/")
    public String findTrainer(Model model, @RequestParam Integer trainerId){
        Optional<TrainerEntity> trainer = adminService.findTrainerById(trainerId);
        if(trainer.isEmpty()){
            //Return trainer not found
            return "/fragments/trainer-details";
        }else {
            model.addAttribute("trainer", trainer.get());
            return "/fragments/trainer-details";
        }
    }

    @PostMapping("/add-trainer")
    public String addTrainer(@RequestParam Integer userId, @RequestParam Integer groupId, @RequestParam String firstName, @RequestParam String lastName){
        TrainerEntity trainer = new TrainerEntity();
        Optional<UserEntity> user = userService.findByUserId(userId);
        Optional<GroupEntity> group = groupService.findById(groupId);
        if (group.isPresent()){
            trainer.setGroup(group.get());
        }
        if (user.isEmpty()){
            //Return user is empty, redirect back to form to fill in an appropriate user
        } else {
            trainer.setUser(user.get());
        }
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        adminService.saveTrainer(trainer);
        int trainerId = trainer.getTrainerId();
        return "redirect:/trainers";
    }

    @PostMapping("/edit-trainer")
    public String editTrainer(@RequestParam int trainerId, @RequestParam String firstName, @RequestParam String lastName){
        adminService.editTrainer(trainerId, firstName, lastName);
        return "redirect:/trainers";
    }

    @PostMapping("/remove-trainer")
    public String removeTrainer(@RequestParam int trainerId, boolean confirmation){
        if(confirmation){
            adminService.deleteTrainerById(trainerId);
        }
        return "redirect:/trainers";
    }
}
