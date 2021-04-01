package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.AdminAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Here is the Admin Service. This service will be responsible for the Admins functionality.
 * Primarily we'll be using this service to manage the Trainer Entities; adding, finding and removing as required.
 *
 * @author Aaron Banjoko
 */
@Service
public class AdminService implements AdminAppService {

    private final TrainerRepository trainerRepository;

    public AdminService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    /**
     * This method saves a Trainer Entity to the database.
     */
    @Override
    public TrainerEntity saveTrainer(TrainerEntity trainer) {
        return trainerRepository.save(trainer);
    }

    /**
     * This method deletes the trainer entity from the database.
     * It takes a trainerEntity as a parameter.
     * @param trainer the current trainer entity
     */
    @Override
    public void deleteTrainer(TrainerEntity trainer) {
        trainerRepository.delete(trainer);
    }

    /**
     * This method deletes a trainer entity from the database via the trainer_id.
     * It takes a trainer-id as its parameter.
     * @param trainerId the id of the trainer to be removed.
     */
    public void deleteTrainerById(int trainerId) {
        trainerRepository.deleteTrainerById(trainerId);
    }

    /**
     * A method to find a trainer via trainer_id.
     * @param trainerId The id of the trainer.
     * @return the trainer entity found.
     */
    @Override
    public TrainerEntity findTrainerById(int trainerId) {
        return trainerRepository.findTrainerById(trainerId);
    }

    /**
     * This method produces an iterable of all trainers in the database.
     * @return an iterable of all trainers.
     */
    @Override
    public Iterable<TrainerEntity> findAllTrainers(){
        return trainerRepository.findAll();
    }
}
