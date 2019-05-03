package com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedTransferDetailsByIpServiceRequest implements CommonServiceRequest {

    private Long ipAdmissionID;
    //constructor

    public GetBedTransferDetailsByIpServiceRequest(Long ipAdmissionID) {
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
