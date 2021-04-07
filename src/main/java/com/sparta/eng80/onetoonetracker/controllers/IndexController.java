package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.entities.*;
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
    private GroupService streamService;

    @Autowired
    public IndexController(SecurityService securityService, GroupService groupService, TrainerService trainerService, GroupService streamService) {
        this.securityService = securityService;
        this.groupService = groupService;
        this.trainerService = trainerService;
        this.streamService = streamService;
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
                    model.addAttribute("allGroups", groupService.findAll());
                    model.addAttribute("allUnassignedTrainers", trainerService.findAllUnassigned());
                    model.addAttribute("allStreams", streamService.findAll());
                    break;
                case "ROLE_ADMIN":
                    model.addAttribute("allGroups", groupService.findAll());
                    model.addAttribute("trainers", trainerService.findAll());
                    break;
                case "ROLE_TRAINEE":
                    TraineeEntity trainee = securityService.getCurrentUser().getTrainee();
                    GroupEntity group = trainee.getGroup();
                    TrainerEntity traineesTrainer = group.getTrainer();
                    StreamEntity stream = group.getStream();
                    Set<FeedbackEntity> traineesFeedbackSheets = trainee.getFeedbacks();
                    int duration = stream.getDuration();
                    LocalDate startDate = group.getStartDate().toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    long currentWeek = ChronoUnit.WEEKS.between(startDate, currentDate) + 1;

                    if (currentWeek > duration) {
                        currentWeek = duration;
                    }

                    Map<Long, FeedbackEntity> feedbackMappedToWeek = new HashMap<>();
                    for (FeedbackEntity feedback:traineesFeedbackSheets) {
                        long feedbackWeek = ChronoUnit.WEEKS.between(startDate, feedback.getDeadline().toLocalDate()) + 1;
                        if (feedbackWeek <= currentWeek) {
                            feedbackMappedToWeek.put(feedbackWeek, feedback);
                        }
                    }

                    model.addAttribute("trainee", trainee);
                    model.addAttribute("trainer", traineesTrainer);
                    model.addAttribute("group", group);
                    model.addAttribute("stream", stream);
                    model.addAttribute("currentWeek", currentWeek);
                    model.addAttribute("feedbackWeeks", feedbackMappedToWeek);
                    break;
            }
            return "index";
        }
        return "login";
    }
}
