package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.*;
import com.sparta.eng80.onetoonetracker.repositories.*;
import com.sparta.eng80.onetoonetracker.security.PasswordEncryptor;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

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
    public Optional<TrainerEntity> findById(int id) { return trainerRepository.findById(id); }

    @Override
    public Iterable<TrainerEntity> findAll() { return trainerRepository.findAll(); }

    @Override
    public TrainerEntity save(TrainerEntity trainerEntity) { return trainerRepository.save(trainerEntity); }

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
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            userEntity.setPassword(passwordEncryptor.encode("password"));
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
        String email = firstName.toLowerCase().toCharArray()[0] + lastName.trim().toLowerCase() +"@sparta.com";
        if(traineeService.findUserByEmail(email).isEmpty()){
            return email;
        } else {
        int i = 1;
        while(true){
            email = firstName.toLowerCase().toCharArray()[0] + lastName.trim().toLowerCase() + i +"@sparta.com";
            if (traineeService.findUserByEmail(email).isEmpty()) {
                return email;
            } else {
                i++;
            }
        }
    }
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
        Optional<TraineeEntity> trainee = traineeService.findById(traineeId);
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

    public boolean disableTraineeLogin(int traineeId) {
        Optional<TraineeEntity> trainee = traineeService.findById(traineeId);
        boolean wasDisabled = false;
        if (trainee.isPresent()) {
            UserEntity user = trainee.get().getUser();
            user.setEnabled(false);
            userRepository.save(user);
            wasDisabled = true;
        } else {
            wasDisabled = false;
        }
        return wasDisabled;
    }
}
