package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(UserEntity user, @RequestParam(value = "techGrade") char techGrade, @RequestParam(value = "consultGrade") char consultGrade,
                               @RequestParam(value = "traineeStop") String traineeStop, @RequestParam(value = "traineeStop") String traineeStart,
                               @RequestParam(value = "traineeStop") String traineeContinue){
        Integer id = user.getUserId();
        feedbackService.updateGrades(id, techGrade, consultGrade);
        feedbackService.updateTraineeSSC(id, traineeStop, traineeStart, traineeContinue);
        return "index";
    }
}
