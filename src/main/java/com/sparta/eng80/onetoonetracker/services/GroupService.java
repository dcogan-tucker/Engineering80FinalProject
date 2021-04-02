package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.repositories.GroupRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.GroupAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService implements GroupAppService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Optional<GroupEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<GroupEntity> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public GroupEntity save(GroupEntity groupEntity) {
        return null;
    }

    @Override
    public Optional<GroupEntity> findByName(String name) {
        return null;
    }

    @Override
    public Iterable<GroupEntity> findByStreamId(int id) {
        return null;
    }
}
