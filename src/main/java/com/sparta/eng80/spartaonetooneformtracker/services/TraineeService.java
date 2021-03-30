package com.sparta.eng80.spartaonetooneformtracker.services;

import com.sparta.eng80.spartaonetooneformtracker.entities.TraineeEntity;
import com.sparta.eng80.spartaonetooneformtracker.services.interfaces.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraineeService implements UserAppService<TraineeEntity> {

    @Override
    public Optional<TraineeEntity> findById() {
        return Optional.empty();
    }

    @Override
    public Iterable<TraineeEntity> findAll() {
        return null;
    }

    @Override
    public TraineeEntity save(TraineeEntity traineeEntity) {
        return null;
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
    public Iterable<TraineeEntity> findByGroupId(int id) {
        return null;
    }

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
