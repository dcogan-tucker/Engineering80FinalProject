package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;
import com.sparta.eng80.onetoonetracker.services.*;
import com.sparta.eng80.onetoonetracker.utilities.TrainerTraineeEntity;
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
    private final StreamService streamService;
    private final TrainerService trainerService;
    private final TraineeService traineeService;

    @Autowired
    public IndexController(SecurityService securityService, GroupService groupService, StreamService streamService, TrainerService trainerService, TraineeService traineeService) {
        this.securityService = securityService;
        this.groupService = groupService;
        this.streamService = streamService;
        this.trainerService = trainerService;
        this.traineeService = traineeService;
    }

    @GetMapping("/")
    public String method(ModelMap model) {
        if(securityService.isAuthenticated()){
            switch (securityService.getCurrentUser().getRole()) {
                case "ROLE_TRAINER":
                    TrainerEntity trainer = securityService.getCurrentUser().getTrainer();
                    Iterable<TraineeEntity> trainees = traineeService.findByGroupId(trainer.getGroup().getGroupId());
                    Iterable<FeedbackEntity> allFeedback = groupService.findAllFeedbackFromGroup(trainer.getGroup().getGroupId());
                    ArrayList<TrainerTraineeEntity> feedbackSheetsInCorrectOrder = new ArrayList<>();

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

                    /*
                    for (TraineeEntity traineeEntity : trainees) {
                        TrainerTraineeEntity trainerTraineeEntity = new TrainerTraineeEntity();
                        trainerTraineeEntity.setTraineeEntity(traineeEntity);
                        boolean found = false;
                        if (allFeedback.iterator().hasNext()) {
                            for (Object feedbackEntity : allFeedback) {
                                if (((FeedbackEntity) feedbackEntity).getTrainee().getTraineeId() == traineeEntity.getTraineeId()) {
                                    trainerTraineeEntity.setFeedbackEntity(((FeedbackEntity) feedbackEntity));
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            FeedbackEntity feedbackEntity = new FeedbackEntity();
                            feedbackEntity.setStatus(Status.IN_PROGRESS);
                            trainerTraineeEntity.setFeedbackEntity(feedbackEntity);
                        }
                        feedbackSheetsInCorrectOrder.add(trainerTraineeEntity);
                    }
                    */

                    model.addAttribute("trainer", trainer);
                    model.addAttribute("trainees", trainees);
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
