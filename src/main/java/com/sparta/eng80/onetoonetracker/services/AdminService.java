package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.AdminAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminAppService {

    private final TrainerRepository trainerRepository;

    public AdminService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public TrainerEntity saveTrainer(TrainerEntity trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(TrainerEntity trainer) {
        trainerRepository.delete(trainer);
    }

    public void deleteTrainerById(int trainerId) {
        trainerRepository.deleteTrainerById(trainerId);
    }

    @Override
    public TrainerEntity findTrainerById(int trainerId) {
        return trainerRepository.findTrainerById(trainerId);
    }

    @Override
    public Iterable<TrainerEntity> findAllTrainers(){
        return trainerRepository.findAll();
    }
}
