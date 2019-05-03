package com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedMovementDetailsByIpServiceRequest implements CommonServiceRequest {
    private Long ipAdmissionID;
    //constructor

    public GetBedMovementDetailsByIpServiceRequest(Long ipAdmissionID) {
        this.ipAdmissionID = ipAdmissionID;
    }

    //getters and setters

    public Long getIpAdmissionID() {
        return ipAdmissionID;
    }

    public void setIpAdmissionID(Long ipAdmissionID) {
        this.ipAdmissionID = ipAdmissionID;
    }
}
