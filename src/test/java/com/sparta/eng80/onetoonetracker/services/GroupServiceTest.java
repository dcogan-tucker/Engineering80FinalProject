package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;
import com.sparta.eng80.onetoonetracker.repositories.StreamRepository;
import com.sparta.eng80.onetoonetracker.repositories.TrainerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashSet;

/**
 * This class is used to test the methods of the GroupService.
 * The AdminService is autowired.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {
    @Autowired
    private GroupService groupService;

    private GroupEntity group;

    /**
     * Tests if the GroupService has been found and connected.
     * This test is run before any other test as it is essential.
     */
    @Test
    @Order(1)
    void contextLoads() {
        Assertions.assertNotNull(groupService);
    }

    /**
     * Tests the save method from the GroupService.
     * If a group is saved the GroupEntity of the saved group should be returned.
     */
    @Test
    @Order(2)
    public void saveTest(){
        group = new GroupEntity();

        group.setGroupName("ServiceTest");
        group.setStartDate(new Date(2020, 5, 1));
        group.setStream(null);
        group.setTrainer(null);
        group.setTrainees(new HashSet<>());
        group.setFeedbacks(new HashSet<>());

        groupService.save(group);

        Assertions.assertNotNull(groupService.findByName("ServiceTest"));
    }

    /**
     * Tests the findById method from the GroupService.
     * By giving a valid groupId a group entity should be returned.
     */
    @Test
    @Order(3)
    public void findGroupByIdTest(){
        Assertions.assertNotNull(groupService.findById(1));
    }

    @Test
    @Order(4)
    public void findAllTest(){
        Assertions.assertNotNull(groupService.findAll().iterator().next());
    }

    @Test
    @Order(5)
    public void getFeedbackFromGroup(){
        this.group = groupService.findByName("Engineering 80").get();
        int id = group.getGroupId();
        Assertions.assertNotNull(group.getFeedbacks());
    }

    @Test
    @Order(6)
    public void deleteGroupByIdTest(){
        this.group = groupService.findByName("ServiceTest").get();
        int id = group.getGroupId();
        groupService.deleteById(id);
        Assertions.assertTrue(groupService.findById(id).isEmpty());
    }
}
