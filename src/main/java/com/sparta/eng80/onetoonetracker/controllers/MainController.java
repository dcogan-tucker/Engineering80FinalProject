package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.services.SecurityService;
import com.sparta.eng80.onetoonetracker.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Set;

@Controller
public class MainController {

    private UserService userService;
    private SecurityService securityService;

    public MainController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping("/competencies")
    public String competencies() {
        return "/fragments/competencies";
    }

    @RequestMapping("/profile")
    public String viewProfile(ModelMap model){
        UserEntity user = securityService.getCurrentUser();
        if(user.getRole().equals("ROLE_TRAINEE")){
            model.addAttribute("name", user.getTrainee().getFirstName() + " " + user.getTrainee().getLastName());

            Set<FeedbackEntity> feedbacks = user.getTrainee().getFeedbacks();
            String[] grades = getAverageGrades(feedbacks);
            model.addAttribute("grades", grades);

            String latestSubmission = getLatestSubmissionTime(feedbacks);
            model.addAttribute("latestSubmissionTime", latestSubmission);

            String averageSubmission = getAverageSubmissionTime(feedbacks);
            model.addAttribute("averageSubmissionTime", averageSubmission);

        } else if(user.getRole().equals("ROLE_TRAINER")){
            model.addAttribute("name", user.getTrainer().getFirstName() + " " + user.getTrainer().getLastName());
        } else {
            model.addAttribute("name", user.getRole());
        }
        model.addAttribute("user", user);

        return "profile";
    }

    private String getLatestSubmissionTime(Set<FeedbackEntity> feedbacks) {
        Iterator<FeedbackEntity> feedbackIter = feedbacks.iterator();
        FeedbackEntity feedback;
        Date submitted = null;
        Date due = null;
        while (feedbackIter.hasNext()) {
            feedback = feedbackIter.next();
            submitted = feedback.getSubmitted();
            if (submitted != null) {
                due = feedback.getDeadline();
                if (due != null) {
                    break;
                }
            }
        }

        while (feedbackIter.hasNext()) {
            feedback = feedbackIter.next();
            Date newDue = feedback.getDeadline();
            if (newDue.after(due)) {
                Date newSubmitted = feedback.getSubmitted();
                if (newSubmitted != null) {
                    due = newDue;
                    submitted = newSubmitted;
                }
            }
        }
        long days = ChronoUnit.DAYS.between(submitted.toLocalDate(), due.toLocalDate());

        return Math.abs(days) + " day(s) " + (days > 0 ? "early" : "late") + ".";

    }

    private String getAverageSubmissionTime(Set<FeedbackEntity> feedbacks) {
        long totalDays = 0;
        int count = 0;
        for (FeedbackEntity feedback : feedbacks) {
            Date submitted = feedback.getSubmitted();
            if (submitted != null) {
                Date due = feedback.getDeadline();
                if (due != null) {
                    totalDays += ChronoUnit.DAYS.between(submitted.toLocalDate(), due.toLocalDate());
                    count ++;
                }
            }
        }

        return (Math.abs(totalDays) / count) + " day(s) " + (totalDays > 0 ? "early" : "late") + ".";
    }

    private String[] getAverageGrades(Set<FeedbackEntity> feedbacks) {
        String[] grades = new String[2];
        int tech = 0;
        int consultant = 0;
        for (FeedbackEntity feedback : feedbacks) {
            String techGrade = feedback.getTechnicalGrade();
            if (techGrade != null) {
                tech +=  techGrade.toCharArray()[0];
            }
            String consGrade = feedback.getConsultantGrade();
            if (consGrade != null) {
                consultant += consGrade.toCharArray()[0];
            }
        }
        grades[0] = String.valueOf((char) (tech / feedbacks.size()));
        grades[1] = String.valueOf((char) (consultant / feedbacks.size()));
        return grades;
    }
}
