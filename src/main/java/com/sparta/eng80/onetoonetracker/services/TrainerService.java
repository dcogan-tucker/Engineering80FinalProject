package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.*;
import com.sparta.eng80.onetoonetracker.repositories.*;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class TrainerService implements UserAppService<TrainerEntity> {

    private final StreamRepository streamRepository;
    private final GroupRepository groupRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeService traineeService;
    private final UserRepository userRepository;

    public TrainerService(StreamRepository streamRepository, GroupRepository groupRepository, TrainerRepository trainerRepository, TraineeService traineeService, UserRepository userRepository) {
        this.streamRepository = streamRepository;
        this.groupRepository = groupRepository;
        this.trainerRepository = trainerRepository;
        this.traineeService = traineeService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<TrainerEntity> findById(int id) {
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

    public boolean createNewGroup(StreamEntity streamEntity, TrainerEntity trainerEntity, String groupName, java.sql.Date startDate) {
        // check stream id is valid
        // check trainer id is valid
        // check groupName is not null or empty string
        if (streamRepository.existsById(streamEntity.getStreamId())
            && trainerRepository.existsById(trainerEntity.getTrainerId())
                && groupName != null && !groupName.equals("")
        ) {
            // add GroupEntity to database
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setGroupName(groupName);
            groupEntity.setStream(streamEntity);
            groupEntity.setStartDate(startDate);
            groupEntity = groupRepository.save(groupEntity);
            // add new TrainerEntity to database with the groupID just added.
            TrainerEntity newTrainerEntity = new TrainerEntity();
            newTrainerEntity.setUser(trainerEntity.getUser());
            newTrainerEntity.setFirstName(trainerEntity.getFirstName());
            newTrainerEntity.setLastName(trainerEntity.getLastName());
            newTrainerEntity.setGroup(groupEntity);
            return true;
        } else {
            return false;
        }
    }

    public TraineeEntity addNewTrainee(GroupEntity groupEntity, String firstName, String lastName, String role) {
        //add checks
        if (
                groupRepository.existsById(groupEntity.getGroupId())
                && firstName != null && !firstName.equals("")
                && lastName != null && !lastName.equals("")
                && role != null && !role.equals("")
        ) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(generateUniqueEmail(firstName, lastName));
            userEntity.setPassword("password");
            userEntity.setRole(role);
            userEntity = userRepository.save(userEntity);
            TraineeEntity traineeEntity = new TraineeEntity();
            traineeEntity.setUser(userEntity);
            traineeEntity.setFirstName(firstName);
            traineeEntity.setLastName(lastName);
            traineeEntity.setGroup(groupEntity);
            traineeService.save(traineeEntity);
            return traineeEntity;
        } else {
            return null;
        }
    }

    private String generateUniqueEmail(String firstName, String lastName) {
        String potentialNewEmailAddress = firstName.substring(0, 1).toUpperCase();
        if (lastName.length() < 3) {
            potentialNewEmailAddress += lastName.substring(0, 1).toUpperCase();
        } else {
            potentialNewEmailAddress += lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        }
        potentialNewEmailAddress += "@spartaglobal.com";
        if (traineeService.findByEmail(potentialNewEmailAddress).isEmpty()) {
            return potentialNewEmailAddress;
        } else {
            generateUniqueEmail(firstName, lastName + 1);
        }
        return potentialNewEmailAddress;
    }
}
