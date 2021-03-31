package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

public interface AdminAppService {

    TrainerEntity saveTrainer(TrainerEntity trainer);
    void deleteTrainer(TrainerEntity trainer);
    TrainerEntity findTrainerById(int trainerId);
    Iterable<TrainerEntity> findAllTrainers();
}
