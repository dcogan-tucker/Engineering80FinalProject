package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.FeedbackService;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final SecurityService securityService;
    private int feedbackID;

    public FeedbackController(FeedbackService feedbackService, SecurityService securityService) {
        this.feedbackService = feedbackService;
        this.securityService = securityService;
    }

    @GetMapping("/feedback")
    public String getFeedback(Model model , @RequestParam(value = "id") Integer id){
        Optional<FeedbackEntity> feedbackEntity = feedbackService.findById(id);
        FeedbackEntity feedback = feedbackEntity.get();
        feedbackID = feedback.getFeedbackId();
        model.addAttribute("feedback", feedback);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute("feedback") FeedbackEntity feedback){
        UserEntity user = securityService.getCurrentUser();
        FeedbackEntity feedbackEntity = feedbackService.findById(feedbackID).get();
        feedbackEntity.setTraineeStop(feedback.getTraineeStop());
        feedbackEntity.setTraineeStart(feedback.getTraineeStart());
        feedbackEntity.setTraineeContinue(feedback.getTraineeContinue());
        feedbackEntity.setConsultantGrade(feedback.getConsultantGrade());
        feedbackEntity.setTechnicalGrade(feedback.getTechnicalGrade());

        if(user.getRole().equals("ROLE_TRAINEE")){
            feedbackService.update(feedbackEntity);
        }

        return "feedback";
    }
}
