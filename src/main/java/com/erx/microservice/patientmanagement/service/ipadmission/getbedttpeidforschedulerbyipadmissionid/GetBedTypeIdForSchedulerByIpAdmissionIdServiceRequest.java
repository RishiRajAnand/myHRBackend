package com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid;

/*
* created by Raushan on 05-10-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

import java.time.LocalDateTime;

public class GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest implements CommonServiceRequest {

    private Long ipAdmissionID;

    private String type;

    private String cutOffTime;
    //constructor

    public GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest(Long ipAdmissionID, String type, String cutOffTime) {
        this.ipAdmissionID = ipAdmissionID;
        this.type = type;
        this.cutOffTime = cutOffTime;
    }


    //getters and setters

    public Long getIpAdmissionID() {
        return ipAdmissionID;
    }

    public void setIpAdmissionID(Long ipAdmissionID) {
        this.ipAdmissionID = ipAdmissionID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public void setCutOffTime(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }

}