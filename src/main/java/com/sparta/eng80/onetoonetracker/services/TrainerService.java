package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.repositories.GroupRepository;
import com.sparta.eng80.onetoonetracker.repositories.StreamRepository;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService implements UserAppService<TrainerEntity> {

    private final StreamRepository streamRepository;
    private final GroupRepository groupRepository;
    private final TrainerRepository trainerRepository;

    public TrainerService(StreamRepository streamRepository, GroupRepository groupRepository, TrainerRepository trainerRepository) {
        this.streamRepository = streamRepository;
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Optional<TrainerEntity> findById() {
        return Optional.empty();
    }

    @Override
    public Iterable<TrainerEntity> findAll() {
        return null;
    }

    @Override
    public TrainerEntity save(TrainerEntity trainerEntity) {
        return null;
    }

    @Override
    public Optional<TrainerEntity> findByUserId(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<TrainerEntity> findByEmail(String name) {
        return Optional.empty();
    }

    @Override
    public Iterable<TrainerEntity> findByGroupId(int id) {
        return null;
    }

    @Override
    public Iterable<TrainerEntity> findByFirstName(String name) {
        return null;
    }

    @Override
    public Iterable<TrainerEntity> findByLastName(String name) {
        return null;
    }

    @Override
    public Iterable<TrainerEntity> findByName(String first, String last) {
        return null;
    }

    public boolean createNewGroup()

}
