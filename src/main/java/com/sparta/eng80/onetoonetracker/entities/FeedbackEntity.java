package com.sparta.eng80.onetoonetracker.entities;

import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;

import java.sql.Date;

public class FeedbackEntity {

    private int feedbackId;
    private Date deadline;
    private Date submitted;
    private String traineeStop;
    private String traineeStart;
    private String traineeContinue;
    private String trainerStop;
    private String trainerStart;
    private String getTrainerContinue;
    private Status status;
    private boolean overdue;

    private TraineeEntity trainee;
    private TrainerEntity trainer;
    private GroupEntity group;

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public String getTraineeStop() {
        return traineeStop;
    }

    public void setTraineeStop(String traineeStop) {
        this.traineeStop = traineeStop;
    }

    public String getTraineeStart() {
        return traineeStart;
    }

    public void setTraineeStart(String traineeStart) {
        this.traineeStart = traineeStart;
    }

    public String getTraineeContinue() {
        return traineeContinue;
    }

    public void setTraineeContinue(String traineeContinue) {
        this.traineeContinue = traineeContinue;
    }

    public String getTrainerStop() {
        return trainerStop;
    }

    public void setTrainerStop(String trainerStop) {
        this.trainerStop = trainerStop;
    }

    public String getTrainerStart() {
        return trainerStart;
    }

    public void setTrainerStart(String trainerStart) {
        this.trainerStart = trainerStart;
    }

    public String getGetTrainerContinue() {
        return getTrainerContinue;
    }

    public void setGetTrainerContinue(String getTrainerContinue) {
        this.getTrainerContinue = getTrainerContinue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public TraineeEntity getTrainee() {
        return trainee;
    }

    public void setTrainee(TraineeEntity trainee) {
        this.trainee = trainee;
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
