package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.repositories.FeedbackRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.FeedbackAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService implements FeedbackAppService {

    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Optional<FeedbackEntity> findByTraineeStop(String traineeStop){return feedbackRepository.findByTraineeStop(traineeStop);}

    public void removeById(int feedbackId){feedbackRepository.removeById(feedbackId);}

    @Override
    public Optional<FeedbackEntity> findById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Iterable<FeedbackEntity> findAll() {
        return null;
    }

    @Override
    public FeedbackEntity save(FeedbackEntity feedbackEntity) {
        return feedbackRepository.save(feedbackEntity);
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
