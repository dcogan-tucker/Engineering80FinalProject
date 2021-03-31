package com.sparta.eng80.onetoonetracker.entities;

import java.util.Set;

public class TrainerEntity {

    private int trainerId;
    private String firstName;
    private String lastName;

    private UserEntity user;
    private GroupEntity group;
    private Set<FeedbackEntity> feedbacks;

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
