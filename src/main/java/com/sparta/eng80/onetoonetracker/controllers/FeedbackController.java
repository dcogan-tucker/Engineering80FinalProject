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

    public FeedbackController(FeedbackService feedbackService, SecurityService securityService) {
        this.feedbackService = feedbackService;
        this.securityService = securityService;
    }

    @GetMapping("/feedback")
    public String getFeedback(Model model , @RequestParam("id") int id){
        Optional<FeedbackEntity> feedbackEntity = feedbackService.findById(id);
        FeedbackEntity feedback = feedbackEntity.get();
        model.addAttribute("feedback", feedback);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute("feedback") FeedbackEntity feedback){
        UserEntity user = securityService.getCurrentUser();
        if(user.getRole().equals("ROLE_TRAINEE")){
            feedbackService.update(feedback);
        }

        return "feedback";
    }
}
