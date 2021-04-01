package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;

/**
 * An Entity class represents and stores data from the user table
 */
@Entity
@Table(name = "user", schema = "1_to_1_tracker")
public class UserEntity {

    /**
     * A unique identifier for a user instance
     */
    private int userId;

    /**
     * The email address of the user
     */
    private String email;

    /**
     * The password that secures the users account
     */
    private String password;

    /**
     * Used to determine whether the user is a trainer, trainee or admin
     */
    private String role;

    /**
     * A value to determine whether the user account is still being used
     */
    private boolean enabled;

    /**
     * A value to determine whether the user has changed their password from the temporary password
     */
    private boolean passwordChanged;

    /**
     * Links the user account to the a trainer only if user is a trainer
     * @see TrainerEntity
     */
    private TrainerEntity trainer;

    /**
     * Links the user account to the a trainee only if user is a trainee
     * @see TraineeEntity
     */
    private TraineeEntity trainee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    @Basic
    @Column(name = "password_changed")
    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public void setEnabled(boolean active) {
        this.enabled = active;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public TraineeEntity getTrainee() {
        return trainee;
    }

    public void setTrainee(TraineeEntity trainee) {
        this.trainee = trainee;
    }
}
