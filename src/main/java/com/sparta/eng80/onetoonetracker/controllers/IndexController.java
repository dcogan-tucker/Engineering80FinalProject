package com.sparta.eng80.onetoonetracker.controllers;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.entities.*;
import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;
import com.sparta.eng80.onetoonetracker.services.*;
import com.sparta.eng80.onetoonetracker.utilities.WeekNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.*;
import java.util.*;

@Controller
public class IndexController {

    private final SecurityService securityService;
    private final GroupService groupService;
    private final TrainerService trainerService;
    private final FeedbackService feedbackService;
    private final StreamService streamService;

    @Autowired
    public IndexController(SecurityService securityService, GroupService groupService, TrainerService trainerService, FeedbackService feedbackService, StreamService streamService) {
        this.securityService = securityService;
        this.groupService = groupService;
        this.trainerService = trainerService;
        this.feedbackService = feedbackService;
        this.streamService = streamService;

    }

    @GetMapping("/")
    public String method(ModelMap model) {
        if (securityService.isAuthenticated()) {
            if (securityService.requiresPasswordChange()) {
                return "redirect:/change-password";
            }
            updateFeedbackForms();
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
                        int weekNo = WeekNumber.getWeekNumber(feedback);

                        List<FeedbackEntity> hasValue = feedbackByWeek.putIfAbsent(weekNo, new ArrayList<>(Arrays.asList(feedback)));
                        if (hasValue != null) {
                            feedbackByWeek.get(weekNo).add(feedback);
                        }
                    }

                    int weeksUnlocked = feedbackByWeek.size();
                    if (weeksUnlocked < trainer.getGroup().getStream().getDuration()) {
                        feedbackByWeek.putIfAbsent(weeksUnlocked + 1, null);
                    }

                    boolean disableUnlock = false;
                    int latestWeek = feedbackByWeek.size() - 1;
                    Iterator<FeedbackEntity> feedbackIter = feedbackByWeek.get(latestWeek).iterator();
                    if (feedbackIter.hasNext()) {
                        FeedbackEntity latestFeedback = feedbackIter.next();
                        Date date = latestFeedback.getDeadline();
                        if (date.equals(getDeadline())) {
                            disableUnlock = true;
                        }
                    }

                    model.addAttribute("trainer", trainer);
                    model.addAttribute("feedbacks", feedbackByWeek);
                    model.addAttribute("allGroups", groupService.findAll());
                    model.addAttribute("allUnassignedTrainers", trainerService.findAllUnassigned());
                    model.addAttribute("allStreams", streamService.findAll());
                    model.addAttribute("disableUnlock", disableUnlock);
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
                    for (FeedbackEntity feedback : traineesFeedbackSheets) {
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


    @PostMapping("/")
    public String unlockWeek() {
        if (securityService.isAuthenticated()) {
            UserEntity currentUser = securityService.getCurrentUser();
            switch (currentUser.getRole()) {
                case "ROLE_TRAINER":
                    TrainerEntity trainer = currentUser.getTrainer();
                    GroupEntity group = trainer.getGroup();
                    FeedbackEntity newFeedback;
                    for (TraineeEntity trainee : group.getTrainees()) {
                        newFeedback = new FeedbackEntity();
                        newFeedback.setTrainee(trainee);
                        newFeedback.setTrainer(trainer);
                        newFeedback.setGroup(group);
                        newFeedback.setDeadline(getDeadline());
                        newFeedback.setStatus(Status.NOT_STARTED);
                        feedbackService.save(newFeedback);
                    }
                    break;
            }
        }
        return "redirect:/";
    }

    private Date getDeadline() {
//        LocalDateTime date = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek <= 4) {
            date.plusDays(4 - dayOfWeek);
            date.plusDays(1);
        } else {
            date.plusDays(4 + (7 - dayOfWeek));
            date.plusDays(1);
        }
//        date.plusHours(23 - date.getHour());
//        date.plusMinutes(59 - date.getMinute());
//        date.plusSeconds(59 - date.getSecond());
//        Timestamp deadline = Timestamp.valueOf(date);
        return Date.valueOf(date);
    }

    private void updateFeedbackForms() {
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        Iterable<FeedbackEntity> feedbackEntities = null;
        switch (securityService.getCurrentUser().getRole()) {
            case "ROLE_TRAINER":
                TrainerEntity trainerEntity = securityService.getCurrentUser().getTrainer();
                feedbackEntities = groupService.findAllFeedbackFromGroup(trainerEntity.getGroup().getGroupId());
                for (FeedbackEntity feedbackEntity : feedbackEntities) {
                    java.sql.Date deadline = feedbackEntity.getDeadline();
                    java.sql.Date submitted = feedbackEntity.getDeadline();
                    if (submitted.after(deadline) || feedbackEntity.getStatus() != Status.SUBMITTED && deadline.before(today)) {
                        feedbackEntity.setOverdue(true);
                    }
                }
                break;
            case "ROLE_TRAINEE":
                TraineeEntity traineeEntity = securityService.getCurrentUser().getTrainee();
                feedbackEntities = traineeEntity.getFeedbacks();
                for (FeedbackEntity feedbackEntity : feedbackEntities) {
                    java.sql.Date deadline = feedbackEntity.getDeadline();
                    java.sql.Date submitted = feedbackEntity.getDeadline();
                    if (submitted.after(deadline) || feedbackEntity.getStatus() != Status.SUBMITTED && deadline.before(today)) {
                        feedbackEntity.setOverdue(true);
                    }
                }
                break;
        }

    }
}

