package com.sparta.eng80.onetoonetracker.entities;

import java.sql.Date;
import java.util.Set;

public class GroupEntity {

    private int groupId;
    private String groupName;
    private Date startDate;

    private StreamEntity stream;
    private TrainerEntity trainer;
    private Set<TraineeEntity> trainees;
    private Set<FeedbackEntity> feedbacks;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public StreamEntity getStream() {
        return stream;
    }

    public void setStream(StreamEntity stream) {
        this.stream = stream;
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public Set<TraineeEntity> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<TraineeEntity> trainees) {
        this.trainees = trainees;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
