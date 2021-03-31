package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.interfaces.AdminAppService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminAppService {
    @Override
    public TrainerEntity saveTrainer(TrainerEntity trainer) {
        return null;
    }

    @Override
    public TrainerEntity deleteTrainer(TrainerEntity trainer) {
        return null;
    }
}
