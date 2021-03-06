package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * An Entity class represents and stores data from the trainer table
 */
@Entity
@Table(name = "trainer", schema = "1_to_1_tracker")
public class TrainerEntity {

    /**
     * A unique identifier for a trainer instance
     */
    private int trainerId;

    /**
     * The first name of the trainer
     */
    private String firstName;

    /**
     * The surname of the trainer
     */
    private String lastName;

    /**
     * The trainers user details
     * @see UserEntity
     */
    private UserEntity user;

    /**
     * The group that the trainer is teaching
     * @see GroupEntity
     */
    private GroupEntity group;

    /**
     * All the feedback forms that the trainer can access
     * @see FeedbackEntity
     */
    private Set<FeedbackEntity> feedbacks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
