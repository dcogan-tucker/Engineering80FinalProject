package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){ Assertions.assertNotNull(userService);}

    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4"
    })
    @Order(1)
    public void findByUserIdTest(int id){
        Assertions.assertNotNull(userService.findByUserId(id));
    }

    @Test
    @Order(2)
    public void findAllTest(){ Assertions.assertTrue(userService.findAll().iterator().hasNext());}

    @Test
    @Order(3)
    public void saveTest(){
        UserEntity user = new UserEntity();
        user.setEmail("userServiceTest");
        user.setPassword("password");
        userService.save(user);
        Assertions.assertNotNull(userService.findByEmail("userServiceTest").get());
    }

    @Test
    @Order(4)
    public void findByEmailTest(){
        Assertions.assertNotNull(userService.findByEmail("userServiceTest").get());
    }

    @Test
    @Order(5)
    public void getAllEmailsTest(){
        Assertions.assertTrue(userService.getAllEmails().iterator().hasNext());
    }

    @Test
    @Order(6)
    public void removeUserTest(){
        userService.removeById(userService.findByEmail("userServiceTest").get().getUserId());
        Assertions.assertTrue(userService.findByEmail("userServiceTest").isEmpty());
    }
}
