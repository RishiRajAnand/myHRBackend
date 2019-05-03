package com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime;

/*
* created by raushan on 25-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

import java.time.LocalDateTime;

public class SaveTherapyCheckInCheckOutTimeServiceRequest implements CommonServiceRequest {

    private LocalDateTime checkInCheckOutTime;
    private Long id;
    private String type;

    //constructor

    public SaveTherapyCheckInCheckOutTimeServiceRequest() {
    }

    public SaveTherapyCheckInCheckOutTimeServiceRequest(LocalDateTime checkInCheckOutTime, Long id, String type) {
        this.checkInCheckOutTime = checkInCheckOutTime;
        this.id = id;
        this.type = type;
    }

    //getters and setters

    public LocalDateTime getCheckInCheckOutTime() {
        return checkInCheckOutTime;
    }

    public void setCheckInCheckOutTime(LocalDateTime checkInCheckOutTime) {
        this.checkInCheckOutTime = checkInCheckOutTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}