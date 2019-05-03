package com.erx.microservice.patientmanagement.service.dischargerequest.savedischargerequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveDischargeRequestServiceResponse extends CommonServiceResponse{

    private Long ipAdmissionRequestId;

    //Constructor

    public SaveDischargeRequestServiceResponse(Long ipAdmissionRequestId) {
        this.ipAdmissionRequestId = ipAdmissionRequestId;
    }

    public SaveDischargeRequestServiceResponse() {
    }

    //Getters and setters

    public Long getIpAdmissionRequestId() {
        return ipAdmissionRequestId;
    }

    public void setIpAdmissionRequestId(Long ipAdmissionRequestId) {
        this.ipAdmissionRequestId = ipAdmissionRequestId;
    }
}
