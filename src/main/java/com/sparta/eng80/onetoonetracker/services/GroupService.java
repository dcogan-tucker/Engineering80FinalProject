package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.services.interfaces.GroupAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService implements GroupAppService {

    @Override
    public Optional<GroupEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<GroupEntity> findAll() {
        return null;
    }

    @Override
    public GroupEntity save(GroupEntity groupEntity) {
        return null;
    }

    @Override
    public GroupEntity findByName(String name) {
        return null;
    }

    @Override
    public GroupEntity findByStreamId(int id) {
        return null;
    }
}
