package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.*;
import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;
import com.sparta.eng80.onetoonetracker.services.*;
import com.sparta.eng80.onetoonetracker.utilities.TrainerTraineeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final TrainerController trainerController;
    private final GroupService groupService;
    private final StreamService streamService;
    private final TrainerService trainerService;

    @Autowired
    public IndexController(SecurityService securityService, TrainerController trainerController, GroupService groupService, StreamService streamService, TrainerService trainerService) {
        this.securityService = securityService;
        this.trainerController = trainerController;
        this.groupService = groupService;
        this.streamService = streamService;
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    public String method(ModelMap model) {
        if(securityService.isAuthenticated()){
            switch (securityService.getCurrentUser().getRole()) {
                case "ROLE_TRAINER":
                    model.addAttribute("allGroups", groupService.findAll());
                    model.addAttribute("allStreams", streamService.findAll());
                    model.addAttribute("allTrainers", trainerService.findAll());
                    Iterable<TraineeEntity> trainees = trainerController.getTrainees(securityService.getCurrentUser().getTrainer());
                    Iterable<FeedbackEntity> feedbackSheets = groupService.findAllFromGroup(securityService.getCurrentUser().getTrainer().getGroup());
                    ArrayList<TrainerTraineeEntity> feedbackSheetsInCorrectOrder = new ArrayList<>();
                    for (TraineeEntity traineeEntity : trainees) {
                        TrainerTraineeEntity trainerTraineeEntity = new TrainerTraineeEntity();
                        trainerTraineeEntity.setTraineeEntity(traineeEntity);
                        boolean found = false;
                        if (feedbackSheets.iterator().hasNext()) {
                            for (Object feedbackEntity : feedbackSheets) {
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
                    model.addAttribute("feedbackStatus", feedbackSheetsInCorrectOrder);
                    break;
                case "ROLE_ADMIN":
                    model.addAttribute("trainers", trainerService.findAll());
                    break;
                case "ROLE_TRAINEE":
                    TraineeEntity trainee = securityService.getCurrentUser().getTrainee();
                    GroupEntity group = trainee.getGroup();
                    TrainerEntity trainer = group.getTrainer();
                    StreamEntity stream = group.getStream();
                    model.addAttribute("trainee", trainee);
                    model.addAttribute("trainer", trainer);
                    model.addAttribute("group", group);
                    model.addAttribute("stream", stream);
                    model.addAttribute("duration", stream.getDuration()+10);
                    break;
            }
            return "index";
        }
        return "login";
    }
}
