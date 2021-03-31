package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

public interface AdminAppService {

    TrainerEntity saveTrainer(TrainerEntity trainer);
    TrainerEntity deleteTrainer(TrainerEntity trainer);
}
