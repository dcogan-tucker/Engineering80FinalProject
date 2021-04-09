package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashSet;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrainerServiceTest {

    @Autowired
    TrainerService trainerService;

    @Autowired
    GroupService groupService;

    @Autowired
    TraineeService traineeService;

    @Autowired
    UserService userService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(trainerService);
        Assertions.assertNotNull(groupService);
        Assertions.assertNotNull(traineeService);
        Assertions.assertNotNull(userService);
    }


    @Test
    @Order(1)
    public void findAllTest(){ Assertions.assertTrue(trainerService.findAll().iterator().hasNext());}

    @ParameterizedTest
    @CsvSource({
            "2", "41"
    })
    @Order(2)
    public void findByIdTest(int id){
        Assertions.assertNotNull(trainerService.findById(id).get());
    }

    @Test
    @Order(3)
    public void findByNameTest(){
        Assertions.assertTrue(trainerService.findByName("Manish", "Gadhvi").iterator().hasNext());
    }

    @Test
    @Order(4)
    public void saveTest(){
        TrainerEntity trainer = new TrainerEntity();
        trainer.setFirstName("TrainerServiceTest");
        trainer.setLastName("TrainerServiceTest");
        trainerService.save(trainer);
        Assertions.assertTrue(trainerService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().hasNext());
    }

    @Test
    @Order(5)
    public void removeTrainerTest(){
        TrainerEntity trainer = trainerService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
        trainerService.removeById(trainer.getTrainerId());
        Assertions.assertTrue(trainerService.findById(trainer.getTrainerId()).isEmpty());
    }

//    @Test
//    @Order(6)
//    public void addNewTraineeTest(){
//        GroupEntity group = new GroupEntity();
//        group.setGroupName("TrainerServiceTest");
//        group.setStartDate(new Date(2020, 5, 1));
//        group.setStream(null);
//        group.setTrainer(null);
//        group.setTrainees(new HashSet<>());
//        group.setFeedbacks(new HashSet<>());
//        groupService.save(group);
//
//        trainerService.addNewTrainee(groupService.findByName("TrainerServiceTest").get(), "TrainerServiceTest", "TrainerServiceTest", "ROLE_TRAINEE");
//        Assertions.assertNotNull(traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next());
//        Assertions.assertTrue(userService.findByEmail("ttrainerservicetest@sparta.com").isPresent());
//        Assertions.assertEquals("TrainerServiceTest",
//                traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next().getGroup().getGroupName());
//    }
//
//    @Test
//    @Order(7)
//    public void removeTraineeFromGroupTest(){
//        TraineeEntity trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        trainerService.removeTraineeFromGroup(trainee.getTraineeId());
//        trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        Assertions.assertNull(trainee.getGroup());
//    }
//
//    @Test
//    @Order(8)
//    public void addTraineeToGroupTest(){
//        TraineeEntity trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        GroupEntity group = groupService.findByName("TrainerServiceTest").get();
//        trainerService.addTraineeToGroup(trainee.getTraineeId(), group.getGroupId());
//        group = groupService.findByName("TrainerServiceTest").get();
//        Assertions.assertFalse(group.getTrainees().isEmpty());
//    }
//
//    @Test
//    @Order(9)
//    public void disableTraineeLoginTest(){
//        TraineeEntity trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        trainerService.disableTraineeLogin(trainee.getTraineeId());
//        trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        Assertions.assertFalse(trainee.getUser().isEnabled());
//    }
//
//    @Test
//    @Order(10)
//    public void removeTestGroupAndTestTrainee(){
//        TraineeEntity trainee = traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().next();
//        int userId = trainee.getUser().getUserId();
//        traineeService.removeTraineeById(trainee.getTraineeId());
//        userService.removeById(userId);
//        GroupEntity group = groupService.findByName("TrainerServiceTest").get();
//        groupService.deleteById(group.getGroupId());
//
//        Assertions.assertTrue(groupService.findByName("TrainerServiceTest").isEmpty());
//        Assertions.assertFalse(traineeService.findByName("TrainerServiceTest", "TrainerServiceTest").iterator().hasNext());
//        Assertions.assertTrue(userService.findByUserId(userId).isEmpty());
//    }
}
