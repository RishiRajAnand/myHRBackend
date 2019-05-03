package com.erx.microservice.patientmanagement.service.iprequest.saveipadmissionrequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveIpAdmissionRequestServiceResponse extends CommonServiceResponse{

    private Long ipAdmissionRequestId;

    //Constructor

    public SaveIpAdmissionRequestServiceResponse(Long ipAdmissionRequestId) {
        this.ipAdmissionRequestId = ipAdmissionRequestId;
    }

    public SaveIpAdmissionRequestServiceResponse() {
    }

    //Getters and setters

    public Long getIpAdmissionRequestId() {
        return ipAdmissionRequestId;
    }

    public void setIpAdmissionRequestId(Long ipAdmissionRequestId) {
        this.ipAdmissionRequestId = ipAdmissionRequestId;
    }
}
