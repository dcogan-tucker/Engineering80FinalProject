package com.sparta.eng80.onetoonetracker;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.datatypes.Status;
import com.sparta.eng80.onetoonetracker.services.FeedbackService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeedbackServiceTest {

    @Autowired
    FeedbackService feedbackService;

    @Test
    @Order(Integer.MIN_VALUE)
    public void contextLoads(){
        Assertions.assertNotNull(feedbackService);
    }

    @ParameterizedTest
    @CsvSource({
            "1","3","30","58"
    })
    @Order(1)
    public void findFeedbackById(int id){
        Assertions.assertNotNull(feedbackService.findById(id));
    }

    @Test
    @Order(3)
    public void saveTest(){
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setTraineeStop("this should never be repeated kdufgsdkugfkbjzbkjxcgvsuhdfhsdfhsjlh");
        feedback.setTraineeStart("trainee start");
        feedback.setTraineeContinue("trainee continue");
        feedback.setTrainerStop("trainer stop");
        feedback.setTrainerStart("trainer start");
        feedback.setTrainerContinue("trainer continue");
        feedback.setTechnicalGrade("A");
        feedback.setConsultantGrade("B");
        feedback.setSubmitted(Date.valueOf(LocalDate.now()));
        feedback.setStatus(Status.IN_PROGRESS);
        feedbackService.save(feedback);
        Assertions.assertNotNull(feedbackService.findById(feedback.getFeedbackId()));
    }

    @Test
    @Order(4)
    public void removeFeedbackTest(){
        FeedbackEntity feedback = feedbackService.findByTraineeStop("this should never be repeated kdufgsdkugfkbjzbkjxcgvsuhdfhsdfhsjlh").get();
        feedbackService.removeById(feedback.getFeedbackId());
        Assertions.assertTrue(feedbackService.findById(feedback.getFeedbackId()).isEmpty());
    }

}
