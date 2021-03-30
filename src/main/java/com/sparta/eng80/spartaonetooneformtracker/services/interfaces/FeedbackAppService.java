package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import com.sparta.eng80.spartaonetooneformtracker.entities.FeedbackEntity;
import com.sparta.eng80.spartaonetooneformtracker.entities.TrainerEntity;

public interface FeedbackAppService extends ApplicationService<FeedbackEntity> {

    Iterable<FeedbackEntity> findByTrainee(TrainerEntity trainee);
    Iterable<FeedbackEntity> findByTrainer(TrainerEntity trainer);
}
