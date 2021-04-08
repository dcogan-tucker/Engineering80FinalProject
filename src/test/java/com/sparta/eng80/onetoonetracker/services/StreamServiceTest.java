package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

/**
 * This class is used to test the methods of the StreamService.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamServiceTest {

    @Autowired
    private StreamService streamService;

    private StreamEntity stream;

    /**
     * Tests if the StreamService has been found and connected.
     * This test is run before any other test as it is essential.
     */
    @Test
    @Order(1)
    void contextLoads() {
        Assertions.assertNotNull(streamService);
    }

    /**
     * Tests the save method from the StreamService.
     * If a stream is saved the StreamService of the saved group should be returned.
     */
    @Test
    @Order(2)
    public void saveTest(){
        stream = new StreamEntity();

        stream.setName("ServiceTest");
        stream.setDescription(null);
        stream.setDuration(12);
        stream.setGroups(new HashSet<>());

        streamService.save(stream);

        Assertions.assertNotNull(streamService.findByName("ServiceTest"));
    }

    /**
     * Tests the findById method from the StreamService.
     * By giving a valid streamId a stream entity should be returned.
     */
    @Test
    @Order(3)
    public void findStreamByIdTest(){
        Assertions.assertNotNull(streamService.findById(1));
    }

    @Test
    @Order(4)
    public void findAllTest(){
        Assertions.assertNotNull(streamService.findAll().iterator().next());
    }

    @Test
    @Order(5)
    public void getGroupsFromStream(){
        this.stream = streamService.findByName("Java Dev").get();
        Assertions.assertNotNull(stream.getGroups());
    }

    @Test
    @Order(6)
    public void deleteStreamByIdTest(){
        this.stream = streamService.findByName("ServiceTest").get();
        int id = stream.getStreamId();
        streamService.deleteById(id);
        Assertions.assertTrue(streamService.findById(id).isEmpty());
    }
}