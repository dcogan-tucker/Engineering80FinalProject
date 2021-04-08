package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TraineeServiceTest {

    @Autowired
    TraineeService traineeService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){ Assertions.assertNotNull(traineeService);}

    @ParameterizedTest
    @CsvSource({
            "1", "2"
    })
    @Order(1)
    public void findByIdTest(int id){
        Assertions.assertNotNull(traineeService.findById(id).get());
    }

    @Test
    @Order(2)
    public void findAllTest(){ Assertions.assertTrue(traineeService.findAll().iterator().hasNext());}

    @ParameterizedTest
    @CsvSource({
            "John Doe", "Mary Smith"
    })
    @Order(3)
    public void findByNameTest(String name){
        String[] names = name.split(" ");
        Assertions.assertNotNull(traineeService.findByName(names[0], names[1]).iterator().next());
    }

    @Test
    @Order(4)
    public void saveTest(){
        TraineeEntity trainee = new TraineeEntity();
        trainee.setFirstName("TraineeServiceTest");
        trainee.setLastName("TraineeServiceTest");
        traineeService.save(trainee);
        Assertions.assertNotNull(traineeService.findByName("TraineeServiceTest", "TraineeServiceTest"));
    }

    @Test
    @Order(5)
    public void removeTraineeByIdTest(){
        TraineeEntity trainee = traineeService.findByName("TraineeServiceTest", "TraineeServiceTest").iterator().next();
        traineeService.removeTraineeById(trainee.getTraineeId());
        Assertions.assertTrue(traineeService.findById(trainee.getTraineeId()).isEmpty());
    }
}
