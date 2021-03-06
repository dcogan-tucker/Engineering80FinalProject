package com.sparta.eng80.onetoonetracker.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TrainerControllerTest {

    @Autowired
    private TrainerController trainerController;

    @Test
    public void loadsContext() {
        Assertions.assertNotNull(trainerController);
    }
}