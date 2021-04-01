package com.sparta.eng80.onetoonetracker.entities;

import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "feedback", schema = "1_to_1_tracker")
public class FeedbackEntity {

    private int feedbackId;
    private Date deadline;
    private Date submitted;
    private String traineeStop;
    private String traineeStart;
    private String traineeContinue;
    private String trainerStop;
    private String trainerStart;
    private String trainerContinue;
    private String technicalGrade;
    private String consultantGrade;
    private Status status;
    private boolean overdue;

    private TraineeEntity trainee;
    private TrainerEntity trainer;
    private GroupEntity group;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Basic
    @Column(name = "deadline_date")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "submitted_date")
    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    @Basic
    @Column(name = "trainee_stop")
    public String getTraineeStop() {
        return traineeStop;
    }

    public void setTraineeStop(String traineeStop) {
        this.traineeStop = traineeStop;
    }

    @Basic
    @Column(name = "trainee_start")
    public String getTraineeStart() {
        return traineeStart;
    }

    public void setTraineeStart(String traineeStart) {
        this.traineeStart = traineeStart;
    }

    @Basic
    @Column(name = "trainee_continue")
    public String getTraineeContinue() {
        return traineeContinue;
    }

    public void setTraineeContinue(String traineeContinue) {
        this.traineeContinue = traineeContinue;
    }

    @Basic
    @Column(name = "trainer_stop")
    public String getTrainerStop() {
        return trainerStop;
    }

    public void setTrainerStop(String trainerStop) {
        this.trainerStop = trainerStop;
    }

    @Basic
    @Column(name = "trainer_start")
    public String getTrainerStart() {
        return trainerStart;
    }

    public void setTrainerStart(String trainerStart) {
        this.trainerStart = trainerStart;
    }

    @Basic
    @Column(name = "trainer_continue")
    public String getTrainerContinue() {
        return trainerContinue;
    }

    public void setTrainerContinue(String trainerContinue) {
        this.trainerContinue = trainerContinue;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Basic
    @Column(name = "is_overdue")
    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    @Basic
    @Column(name = "technical_grade")
    public String getTechnicalGrade(){
        return technicalGrade;
    }

    public void setTechnicalGrade(String technicalGrade){
        this.technicalGrade = technicalGrade;
    }

    @Basic
    @Column(name = "consultant_grade")
    public String getConsultantGrade(){
        return consultantGrade;
    }

    public void setConsultantGrade(String consultantGrade){
        this.consultantGrade = consultantGrade;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id")
    public TraineeEntity getTrainee() {
        return trainee;
    }

    public void setTrainee(TraineeEntity trainee) {
        this.trainee = trainee;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
