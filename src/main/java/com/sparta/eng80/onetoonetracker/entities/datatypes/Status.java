package com.sparta.eng80.onetoonetracker.entities.datatypes;

public enum Status {

    NOT_STARTED("Not Started"), IN_PROGRESS("In Progress"), SUBMITTED("Submitted"), CLOSED("Closed");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
