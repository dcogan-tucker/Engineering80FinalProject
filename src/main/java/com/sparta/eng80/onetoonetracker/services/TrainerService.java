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
            userEntity.setEnabled(true);
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

    /**
     * Adds trainee to group, returning {@code true} if successful, otherwise {@code false}
     * <p>If either {@code traineeId} of {@code groupId} do not exist within the database, then trainee cannot be added</p>
     * <p>Any group the trainee is currently part will be overwritten, since trainee can only be part of one group at a time</p>
     * @param traineeId id of trainee to add to group
     * @param groupId id of group to add trainee to
     * @return boolean value indicating whether trainee was added to group or not
     */
    public boolean addTraineeToGroup(int traineeId, int groupId) {
        Optional<TraineeEntity> trainee = traineeService.findByUserId(traineeId);
        Optional<GroupEntity> group = groupRepository.findById(groupId);

        if (trainee.isPresent() && group.isPresent()) {
            trainee.get().setGroup(group.get());
            traineeService.save(trainee.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes trainee from group, returning {@code true} if successful, otherwise {@code false}
     * <p>If {@code traineeId} does not exist within the database then trainee cannot be removed</p>
     * <p>If {@code trainee} is not in any group then trainee cannot be removed</p>
     * @param traineeId id of trainee to add to group
     * @return boolean value indicating whether trainee was removed from their group or not
     */
    public boolean removeTraineeFromGroup(int traineeId) {
        Optional<TraineeEntity> trainee = traineeService.findByUserId(traineeId);
        boolean wasRemoved = false;

        if (trainee.isPresent()) {
            if (trainee.get().getGroup() != null) {
                trainee.get().setGroup(null);
                traineeService.save(trainee.get());
                wasRemoved = true;
            }
        } else {
            wasRemoved = false;
        }
        return wasRemoved;
    }
}
