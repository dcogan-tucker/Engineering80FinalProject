package com.sparta.eng80.onetoonetracker.utilities;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class NewGroupForm {

    private Integer groupId;
    private String groupName;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date startDate;

    private Integer streamId;
    private Integer trainerId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }
}
