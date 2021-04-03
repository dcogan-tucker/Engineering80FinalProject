package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.repositories.GroupRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.GroupAppService;
import com.sparta.eng80.onetoonetracker.utilities.NewGroupForm;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class GroupService implements GroupAppService {

    private final GroupRepository groupRepository;
    private final StreamService streamService;
    private final TrainerService trainerService;

    public GroupService(GroupRepository groupRepository, StreamService streamService, TrainerService trainerService) {
        this.groupRepository = groupRepository;
        this.streamService = streamService;
        this.trainerService = trainerService;
    }

    @Override
    public Optional<GroupEntity> findById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public Iterable<GroupEntity> findAll() {
        return groupRepository.findAll();
    }

    public Iterable<FeedbackEntity> findAllFromGroup(GroupEntity groupEntity) {
        return groupEntity.getFeedbacks();
    }

    @Override
    public GroupEntity save(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    @Override
    public Optional<GroupEntity> findByName(String name) {
        return null;
    }

    @Override
    public Iterable<GroupEntity> findByStreamId(int id) {
        return null;
    }

    public void addNewGroup(NewGroupForm newGroupForm) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(newGroupForm.getGroupName());
        groupEntity.setStartDate(newGroupForm.getStartDate());
        groupEntity.setStream(streamService.findById(newGroupForm.getStreamId()).get());
        groupEntity.setTrainer(trainerService.findById(newGroupForm.getTrainerId()).get());
        groupEntity.setTrainees(new HashSet<>());
        groupEntity.setFeedbacks(new HashSet<>());
        save(groupEntity);
    }
}
