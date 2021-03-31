package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "stream_group", schema = "1_to_1_tracker")
public class GroupEntity {

    private int groupId;
    private String groupName;
    private Date startDate;

    private StreamEntity stream;
    private TrainerEntity trainer;
    private Set<TraineeEntity> trainees;
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

    @OneToOne(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    @OneToMany(mappedBy = "trainees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TraineeEntity> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<TraineeEntity> trainees) {
        this.trainees = trainees;
    }

    @OneToMany(mappedBy = "feedbacks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
