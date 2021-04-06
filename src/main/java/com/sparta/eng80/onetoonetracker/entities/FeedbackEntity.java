package com.sparta.eng80.onetoonetracker.entities;

import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;

import javax.persistence.*;
import java.sql.Date;

/**
 * An Entity class represents and stores data from the feedback table
 */
@Entity
@Table(name = "feedback", schema = "1_to_1_tracker")
public class FeedbackEntity {

    /**
     * A unique identifier for a feedback instance
     */
    private int feedbackId;

    /**
     * The date that the feedback should be submitted before
     */
    private Date deadline;

    /**
     * The date that the feedback was submitted on
     */
    private Date submitted;

    /**
     * A paragraph of what the trainee thinks they should to stop doing
     */
    private String traineeStop;

    /**
     * A paragraph of what the trainee thinks they should to start doing
     */
    private String traineeStart;

    /**
     * A paragraph of what the trainee thinks they should to continue doing
     */
    private String traineeContinue;

    /**
     * A paragraph of what the trainer thinks the trainee should to stop doing
     */
    private String trainerStop;

    /**
     * A paragraph of what the trainer thinks the trainee should to start doing
     */
    private String trainerStart;

    /**
     * A paragraph of what the trainer thinks the trainee should to continue doing
     */
    private String trainerContinue;

    /**
     * The grade that the trainee believes achieved in technical skills for the week
     */
    private String technicalGrade;

    /**
     * The grade that the trainee believes achieved in consultant skills for the week
     */
    private String consultantGrade;

    /**
     * The current status on the completion of the feedback form
     * @see Status
     */
    private Status status;

    /**
     * The status for whether the feedback form has been submitted before or after the deadline
     */
    private boolean overdue;

    /**
     * The trainee which the feedback relates to
     * @see TraineeEntity
     */
    private TraineeEntity trainee;

    /**
     * The trainer who can view the feedback
     * @see TrainerEntity
     */
    private TrainerEntity trainer;

    /**
     * The group which the trainee who relates to the feedback was apart of when  the feedback is created
     * @see GroupEntity
     */
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
