package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * An Entity class represents and stores data from the stream_group table
 */
@Entity
@Table(name = "stream_group", schema = "1_to_1_tracker")
public class GroupEntity {

    /**
     * A unique identifier for a group instance
     */
    private int groupId;

    /**
     * The name of the group
     */
    private String groupName;

    /**
     * The date which the group starts/started their training
     */
    private Date startDate;

    /**
     * The stream that the group belongs to
     * @see StreamEntity
     */
    private StreamEntity stream;

    /**
     * The trainer/s that are teaching the group
     * @see TrainerEntity
     */
    private TrainerEntity trainer;

    /**
     * All trainees which are assigned to the group
     * @see TraineeEntity
     */
    private Set<TraineeEntity> trainees;

    /**
     * All Feedback for all trainees in the group
     * @see FeedbackEntity
     */
    private Set<FeedbackEntity> feedbacks;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @ManyToOne
    @JoinColumn(name = "stream_id")
    public StreamEntity getStream() {
        return stream;
    }

    public void setStream(StreamEntity stream) {
        this.stream = stream;
    }

    @OneToOne(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TraineeEntity> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<TraineeEntity> trainees) {
        this.trainees = trainees;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
