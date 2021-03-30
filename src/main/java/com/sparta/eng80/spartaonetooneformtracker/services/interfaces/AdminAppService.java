package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import com.sparta.eng80.spartaonetooneformtracker.entities.TrainerEntity;

public interface AdminAppService {

    TrainerEntity saveTrainer(TrainerEntity trainer);
    TrainerEntity deleteTrainer(TrainerEntity trainer);
}
