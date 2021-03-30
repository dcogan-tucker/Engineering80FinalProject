package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

public interface FeedbackAppService extends ApplicationService<FeedbackEntity> {

    Iterable<FeedbackEntity> findByTrainee(TrainerEntity trainee);
    Iterable<FeedbackEntity> findByTrainer(TrainerEntity trainer);
}
