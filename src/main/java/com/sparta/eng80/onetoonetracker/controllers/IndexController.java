package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final GroupService groupService;
    private final TrainerService trainerService;

    @Autowired
    public IndexController(SecurityService securityService, GroupService groupService, TrainerService trainerService) {
        this.securityService = securityService;
        this.groupService = groupService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String method(ModelMap model) {
        if(securityService.isAuthenticated()){
            switch (securityService.getCurrentUser().getRole()) {
                case "ROLE_TRAINER":
                    TrainerEntity trainer = securityService.getCurrentUser().getTrainer();
                    Iterable<FeedbackEntity> allFeedback = groupService.findAllFeedbackFromGroup(trainer.getGroup().getGroupId());

                    List<FeedbackEntity> feedbackOrdered = new ArrayList<>();
                    for (FeedbackEntity feedback : allFeedback) {
                        feedbackOrdered.add(feedback);
                    }
                    feedbackOrdered.sort(Comparator.comparing(FeedbackEntity::getDeadline).reversed());

                    Map<Integer, List<FeedbackEntity>> feedbackByWeek = new HashMap<>();

                    for (FeedbackEntity feedback : feedbackOrdered) {
                        LocalDate startDate = trainer.getGroup().getStartDate().toLocalDate();
                        LocalDate feedbackDate = feedback.getDeadline().toLocalDate();
                        int weekNo = (int) ChronoUnit.WEEKS.between(startDate, feedbackDate) + 1;

                        List<FeedbackEntity> hasValue = feedbackByWeek.putIfAbsent(weekNo, new ArrayList<>(Arrays.asList(feedback)));
                        if (hasValue != null) {
                            feedbackByWeek.get(weekNo).add(feedback);
                        }
                    }

                    model.addAttribute("trainer", trainer);
                    model.addAttribute("feedbacks", feedbackByWeek);
                    break;
                case "ROLE_ADMIN":
                    model.addAttribute("trainers", trainerService.findAll());
                    break;
                case "ROLE_TRAINEE":
                    model.addAttribute("trainee", securityService.getCurrentUser().getTrainee());
                    break;
            }
            return "index";
        }
        return "login";
    }
}
