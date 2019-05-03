package com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid;

/*
* created by Raushan on 06-10-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetAllocatedBedDetailsByIpAdmissionIdServiceRequest implements CommonServiceRequest {

    private Long ipAdmissionId;


    //constructor

    public GetAllocatedBedDetailsByIpAdmissionIdServiceRequest(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }


    //getters and setters


    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }
}