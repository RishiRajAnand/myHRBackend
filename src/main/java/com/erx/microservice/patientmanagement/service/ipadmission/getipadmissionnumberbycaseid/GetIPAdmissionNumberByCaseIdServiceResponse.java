package com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid;

/*
* created by raushan on 18-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class GetIPAdmissionNumberByCaseIdServiceResponse extends CommonServiceResponse {

    private String ipAdmissionNo;

    //Constructor
    public GetIPAdmissionNumberByCaseIdServiceResponse() {
    }

    public GetIPAdmissionNumberByCaseIdServiceResponse(String ipAdmissionNo) {
        this.ipAdmissionNo = ipAdmissionNo;
    }

    //getter and setter


    public String getIpAdmissionNo() {
        return ipAdmissionNo;
    }

    public void setIpAdmissionNo(String ipAdmissionNo) {
        this.ipAdmissionNo = ipAdmissionNo;
    }
}