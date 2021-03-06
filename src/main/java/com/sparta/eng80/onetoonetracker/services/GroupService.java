package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.repositories.GroupRepository;
import com.sparta.eng80.onetoonetracker.repositories.StreamRepository;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.GroupAppService;
import com.sparta.eng80.onetoonetracker.utilities.NewGroupForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class GroupService implements GroupAppService {

    private final GroupRepository groupRepository;
    private final StreamRepository streamRepository;
    private final TrainerRepository trainerRepository;

    public GroupService(GroupRepository groupRepository, StreamRepository streamRepository, TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.streamRepository = streamRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Optional<GroupEntity> findById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public Iterable<GroupEntity> findAll() {
        return groupRepository.findAll();
    }

    public Iterable<FeedbackEntity> findAllFeedbackFromGroup(int id) {
        return groupRepository.findById(id).get().getFeedbacks();
    }

    @Override
    public GroupEntity save(GroupEntity groupEntity) {
        return groupRepository.save(groupEntity);
    }

    @Override
    public Optional<GroupEntity> findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public Iterable<GroupEntity> findByStreamId(int id) {
        return null;
    }

    public void addNewGroup(NewGroupForm newGroupForm) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(newGroupForm.getGroupName());
        groupEntity.setStartDate(newGroupForm.getStartDate());
        groupEntity.setStream(streamRepository.findById(newGroupForm.getStreamId()).get());
        groupEntity.setTrainees(new HashSet<>());
        groupEntity.setFeedbacks(new HashSet<>());
        save(groupEntity);
    }

    public Iterable<GroupEntity> findAllUnassigned() {
        Iterable<GroupEntity> all = findAll();
        ArrayList<GroupEntity> unique = new ArrayList<>();
        for (GroupEntity groupEntity : all) {
            if (groupEntity.getTrainer() == null) {
                unique.add(groupEntity);
            }
        }
        return unique;
    }

    //strictly for testing purposes only
    public void deleteById(int groupId) {
        groupRepository.deleteById(groupId);
    }
}
