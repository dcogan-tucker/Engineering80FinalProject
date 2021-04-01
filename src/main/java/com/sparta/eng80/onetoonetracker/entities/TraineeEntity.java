package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * An Entity class represents and stores data from the trainee table
 */
@Entity
@Table(name = "trainee", schema = "1_to_1_tracker")
public class TraineeEntity {

    /**
     * A unique identifier for a trainee instance
     */
    private int traineeId;

    /**
     * The first name of the trainee
     */
    private String firstName;

    /**
     * The surname of the trainee
     */
    private String lastName;

    /**
     * The trainees user details
     * @see UserEntity
     */
    private UserEntity user;

    /**
     * The group that the trainee is apart of
     * @see GroupEntity
     */
    private GroupEntity group;

    /**
     * All the feedback forms that are related to the trainee
     * @see FeedbackEntity
     */
    private TreeSet<FeedbackEntity> feedbacks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainee_id")
    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = (TreeSet<FeedbackEntity>) feedbacks;
    }
}
