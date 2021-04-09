package com.sparta.eng80.onetoonetracker.utilities;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WeekNumber {

    public static int getWeekNumber(FeedbackEntity feedback){
        LocalDate startDate = feedback.getGroup().getStartDate().toLocalDate();
        LocalDate feedbackDate = feedback.getDeadline().toLocalDate();
        return (int) ChronoUnit.WEEKS.between(startDate, feedbackDate) + 1;
    }

}
