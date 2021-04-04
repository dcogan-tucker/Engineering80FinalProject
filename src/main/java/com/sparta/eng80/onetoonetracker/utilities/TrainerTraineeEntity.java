package com.sparta.eng80.onetoonetracker.utilities;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;

public class TrainerTraineeEntity {

    TraineeEntity traineeEntity;
    FeedbackEntity feedbackEntity;

    public FeedbackEntity getFeedbackEntity() {
        return feedbackEntity;
    }

    public void setFeedbackEntity(FeedbackEntity feedbackEntity) {
        this.feedbackEntity = feedbackEntity;
    }

    public TraineeEntity getTraineeEntity() {
        return traineeEntity;
    }

    public void setTraineeEntity(TraineeEntity traineeEntity) {
        this.traineeEntity = traineeEntity;
    }
}
