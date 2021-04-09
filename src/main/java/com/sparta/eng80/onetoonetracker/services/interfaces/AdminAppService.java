package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

import java.util.Optional;

public interface AdminAppService {

    TrainerEntity saveTrainer(TrainerEntity trainer);
    void deleteTrainer(TrainerEntity trainer);
    Optional<TrainerEntity> findTrainerById(int trainerId);
    Iterable<TrainerEntity> findAllTrainers();
}
