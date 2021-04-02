package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.repositories.TraineeRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TraineeService implements UserAppService<TraineeEntity> {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public Optional<TraineeEntity> findById(int id) {
        return traineeRepository.findById(id);
    }

    @Override
    public Iterable<TraineeEntity> findAll() {
        return traineeRepository.findAll();
    }

    @Override
    public TraineeEntity save(TraineeEntity traineeEntity) {
        return traineeRepository.save(traineeEntity);
    }

    @Override
    public Optional<TraineeEntity> findByUserId(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<TraineeEntity> findByEmail(String name) {
        return Optional.empty();
    }

    @Override
    public Iterable<TraineeEntity> findByGroupId(int id) { return traineeRepository.getAllTraineesFromAGroup(id); }

    @Override
    public Iterable<TraineeEntity> findByFirstName(String name) {
        return null;
    }

    @Override
    public Iterable<TraineeEntity> findByLastName(String name) {
        return null;
    }

    @Override
    public Iterable<TraineeEntity> findByName(String first, String last) {
        return null;
    }
}
