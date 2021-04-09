package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.repositories.TraineeRepository;
import com.sparta.eng80.onetoonetracker.repositories.UserRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraineeService implements UserAppService<TraineeEntity> {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    public TraineeService(TraineeRepository traineeRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
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
    public Optional<TraineeEntity> findByEmail(String email) {
        return Optional.empty();
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findUserEntityByEmailEquals(email);
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
        return traineeRepository.findByName(first, last);
    }

    public void removeTraineeById(int id){
        traineeRepository.removeById(id);
    }
}
