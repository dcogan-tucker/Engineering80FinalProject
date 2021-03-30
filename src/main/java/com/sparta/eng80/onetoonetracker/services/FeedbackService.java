package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.interfaces.FeedbackAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService implements FeedbackAppService {

    @Override
    public Optional<FeedbackEntity> findById() {
        return Optional.empty();
    }

    @Override
    public Iterable<FeedbackEntity> findAll() {
        return null;
    }

    @Override
    public FeedbackEntity save(FeedbackEntity feedbackEntity) {
        return null;
    }

    @Override
    public Iterable<FeedbackEntity> findByTrainee(TrainerEntity trainee) {
        return null;
    }

    @Override
    public Iterable<FeedbackEntity> findByTrainer(TrainerEntity trainer) {
        return null;
    }
}
