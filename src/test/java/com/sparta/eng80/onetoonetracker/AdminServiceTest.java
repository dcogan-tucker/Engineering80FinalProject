package com.sparta.eng80.onetoonetracker;


import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import com.sparta.eng80.onetoonetracker.services.AdminService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class is used to test the methods of the AdminService.
 * The AdminService is autowired.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainerRepository trainerRepository;

    private TrainerEntity trainer;

    /**
     * Tests if the AdminService has been found and connected.
     * This test is run before any other test as it is essential.
     */
    @Test
    @Order(1)
    void contextLoads() {
        Assertions.assertNotNull(adminService);
    }

    /**
     * Tests the saveTrainer method from the AdminService.
     * If a trainer is saved the trainer entity of the saved trainer should be returned.
     */
    @Test
    @Order(2)
    public void saveTrainerTest(){
        trainer = new TrainerEntity();
        trainer.setFirstName("ServiceTest");
        trainer.setLastName("ServiceTest");
        adminService.saveTrainer(trainer);
        this.trainer = trainerRepository.findByName("ServiceTest", "ServiceTest");
        Assertions.assertNotNull(adminService.findTrainerById(trainer.getTrainerId()));
    }

    /**
     * Tests the findTrainerById method from the AdminService.
     * By giving a valid trainerId a trainer entity should be returned.
     */
    @Test
    @Order(3)
    public void findTrainerByIdTest(){
        Assertions.assertNotNull(adminService.findTrainerById(1));
    }

    @Test
    @Order(4)
    public void findAllTrainersTest(){
        Assertions.assertNotNull(adminService.findAllTrainers().iterator().next());
    }

    @Test
    @Order(5)
    public void editTrainerTest(){
        this.trainer = trainerRepository.findByName("ServiceTest", "ServiceTest");
        int id = trainer.getTrainerId();
        adminService.editTrainer(trainer.getTrainerId(), "ServiceTesting", "Lastnamee");
        Assertions.assertEquals("ServiceTesting", adminService.findTrainerById(id).get().getFirstName());
        Assertions.assertEquals("Lastnamee", adminService.findTrainerById(id).get().getLastName());
    }

    @Test
    @Order(6)
    public void deleteTrainerByIdTest(){
        this.trainer = trainerRepository.findByName("ServiceTesting", "Lastnamee");
        int id = trainer.getTrainerId();
        adminService.deleteTrainerById(id);
        Assertions.assertTrue(adminService.findTrainerById(id).isEmpty());
    }

}
