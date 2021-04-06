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

import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.Set;


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
        return "/fragments/home/admin_home";
    }

    @PostMapping("/add-trainer")
    public String addTrainer(@RequestParam String groupId, @RequestParam String firstName, @RequestParam String lastName){
        TrainerEntity trainer = new TrainerEntity();
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
//        if (!groupId.equals("")){
//            Optional<GroupEntity> group = groupService.findById(Integer.parseInt(groupId));
//            if(group.isPresent()){
//                trainer.setGroup(group.get());
//            }
//        }
        UserEntity user = new UserEntity();
        user.setRole("ROLE_TRAINER");
        user.setPassword("password");
        user.setEnabled(true);
        String email = firstName.toLowerCase().toCharArray()[0] +
                lastName.trim().toLowerCase();
        Set<String> emails = userService.getAllEmails();
        if(!emails.contains(email +"@sparta.com")){
            user.setEmail(email +"@sparta.com");
        } else {
            boolean valid = false;
            int i = 1;
            while(!valid){
                if (!emails.contains(email + i + "@sparta.com")) {
                    user.setEmail(email + i + "@sparta.com");
                    valid = true;
                } else i++;
            }
        }
        trainer.setUser(user);
        adminService.saveTrainer(trainer);
        return "redirect:/trainers";
    }

    @PostMapping("/remove-trainer")
    public String removeTrainer(@RequestParam int trainerId, boolean confirmation){
        if(confirmation){
            adminService.deleteTrainerById(trainerId);
        }
        return "redirect:/trainers";
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

    @PostMapping("/edit-trainer")
    public String editTrainer(@RequestParam int trainerId, @RequestParam String firstName, @RequestParam String lastName){
        adminService.editTrainer(trainerId, firstName, lastName);
        return "redirect:/trainers";
    }
}
