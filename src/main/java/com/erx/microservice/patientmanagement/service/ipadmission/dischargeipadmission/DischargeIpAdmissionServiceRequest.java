package com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission;

/*
* created by Brighty on Brighty on 21-06-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DischargeIpAdmissionServiceRequest implements CommonServiceRequest {

    private Long ipAdmissionId;

    //Constructor

    public DischargeIpAdmissionServiceRequest(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public DischargeIpAdmissionServiceRequest() {
    }

    //Getters and setters

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }
}
