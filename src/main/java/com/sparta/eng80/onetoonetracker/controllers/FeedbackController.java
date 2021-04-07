package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;
import com.sparta.eng80.onetoonetracker.services.FeedbackService;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
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
        UserEntity user = securityService.getCurrentUser();
        Optional<FeedbackEntity> feedbackEntity = feedbackService.findById(id);
        FeedbackEntity feedback = feedbackEntity.get();
        if((user.getUserId() != feedback.getTrainee().getUser().getUserId()) && (user.getUserId() != feedback.getTrainer().getUser().getUserId())) {
            return "redirect:/error";
        }
        feedbackID = feedback.getFeedbackId();
        model.addAttribute("feedback", feedback);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute("feedback") FeedbackEntity feedback, @RequestParam(value="action", required=true) String action){
        UserEntity user = securityService.getCurrentUser();
        FeedbackEntity feedbackEntity = feedbackService.findById(feedbackID).get();
        feedbackEntity.setTraineeStop(feedback.getTraineeStop());
        feedbackEntity.setTraineeStart(feedback.getTraineeStart());
        feedbackEntity.setTraineeContinue(feedback.getTraineeContinue());
        feedbackEntity.setConsultantGrade(feedback.getConsultantGrade());
        feedbackEntity.setTechnicalGrade(feedback.getTechnicalGrade());
        if(action.equals("Save")){
            feedbackEntity.setStatus(Status.IN_PROGRESS);
        }else if(action.equals("Submit")){
            feedbackEntity.setStatus(Status.SUBMITTED);
        }

        feedbackEntity.setSubmitted(Date.valueOf(LocalDate.now()));

        if(user.getRole().equals("ROLE_TRAINEE")){
            feedbackService.update(feedbackEntity);
        }

        return "redirect:/";
    }
}
