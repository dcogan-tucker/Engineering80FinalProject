package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

/**
 * Interface to be implemented by a service dealing with feedback entities.
 */
public interface FeedbackAppService extends ApplicationService<FeedbackEntity> {

    /**
     * Finds all feedback for the given trainee from the repository.
     *
     * @param trainee The trainee to find the feedback for.
     * @return An Iterable containing all the feedback for a given trainee.
     */
    Iterable<FeedbackEntity> findByTrainee(TrainerEntity trainee);

    /**
     * Finds all feedback for the given trainer from the repository.
     *
     * @param trainer The trainer to find the feedback for.
     * @return An Iterable containing all the feedback for a given trainer.
     */
    Iterable<FeedbackEntity> findByTrainer(TrainerEntity trainer);
}
