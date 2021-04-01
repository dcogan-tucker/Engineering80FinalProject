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

    // TODO: feedback is present could be extracted into separate method to prevent code being repeated
    public FeedbackEntity updateTraineeSSC(Integer id, String traineeStop, String traineeStart, String traineeContinue){
        Optional<FeedbackEntity> feedbackOptional = feedbackRepository.findById(id);
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        if(feedbackOptional.isPresent()){
             feedbackEntity = feedbackOptional.get();
             feedbackEntity.setTraineeStop(traineeStop);
             feedbackEntity.setTraineeStart(traineeStart);
             feedbackEntity.setTraineeContinue(traineeContinue);
        }
        return feedbackRepository.save(feedbackEntity);
    }

    public FeedbackEntity updateTrainerSSC(Integer id, String trainerStop, String trainerStart, String trainerContinue){
        Optional<FeedbackEntity> feedbackOptional = feedbackRepository.findById(id);
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        if(feedbackOptional.isPresent()){
            feedbackEntity = feedbackOptional.get();
            feedbackEntity.setTrainerStop(trainerStop);
            feedbackEntity.setTrainerStart(trainerStart);
            feedbackEntity.setTrainerContinue(trainerContinue);
        }
        return feedbackRepository.save(feedbackEntity);
    }

    public FeedbackEntity updateGrades(Integer id, String technicalGrade, String consultantGrade){
        Optional<FeedbackEntity> feedbackOptional = feedbackRepository.findById(id);
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        if(feedbackOptional.isPresent()){
            feedbackEntity = feedbackOptional.get();
            feedbackEntity.setTechnicalGrade(technicalGrade);
            feedbackEntity.setConsultantGrade(consultantGrade);
        }
        return feedbackRepository.save(feedbackEntity);
    }

    public FeedbackEntity update(FeedbackEntity feedbackEntity){
        return feedbackRepository.save(feedbackEntity);
    }



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
