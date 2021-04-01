package com.sparta.eng80.onetoonetracker;


import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.services.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class is used to test the methods of the AdminService.
 * The AdminService is autowired.
 */
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    /**
     * Tests if the AdminService has been found and connected.
     * This test is run before any other test as it is essential.
     */
    @Test
    @Order(Integer.MIN_VALUE)
    void contextLoads() {
        Assertions.assertNotNull(adminService);
    }

    /**
     * Tests the saveTrainer method from the AdminService.
     * If a trainer is saved the trainer entity of the saved trainer should be returned.
     */
    @Test
    public void addTrainerTest(){
        TrainerEntity trainer = new TrainerEntity();
        trainer.setFirstName("Test");
        trainer.setLastName("Test");
        Assertions.assertNotNull(adminService.saveTrainer(trainer));
    }

    /**
     * Tests the findTrainerById method from the AdminService.
     * By giving a valid trainerId a trainer entity should be returned.
     */
    @Test
    public void findTrainerByIdTest(){
        Assertions.assertNotNull(adminService.findTrainerById(1));
    }

}
