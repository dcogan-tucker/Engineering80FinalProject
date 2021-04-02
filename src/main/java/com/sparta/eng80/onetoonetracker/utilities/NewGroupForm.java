package com.sparta.eng80.onetoonetracker.utilities;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NewGroupForm {

    private Integer groupId;
    private String groupName;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date startDate;

    private Integer streamId;
    private Integer trainerId;
    private Set<TraineeEntity> trainees;
    private Set<FeedbackEntity> feedbacks;

}
