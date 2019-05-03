package com.erx.microservice.patientmanagement.service.ipadmission.allocatebed;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class AllocateBedServiceResponse extends CommonServiceResponse {

    private String IpAdmissionNumber;
    private String DayCareAdmissionNumber;

    // constructor

    public AllocateBedServiceResponse(String ipAdmissionNumber, String dayCareAdmissionNumber) {
        IpAdmissionNumber = ipAdmissionNumber;
        DayCareAdmissionNumber = dayCareAdmissionNumber;
    }

    public AllocateBedServiceResponse() {

    }

    // getters and setters

    public String getIpAdmissionNumber() {
        return IpAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        IpAdmissionNumber = ipAdmissionNumber;
    }

    public String getDayCareAdmissionNumber() {
        return DayCareAdmissionNumber;
    }

    public void setDayCareAdmissionNumber(String dayCareAdmissionNumber) {
        DayCareAdmissionNumber = dayCareAdmissionNumber;
    }
}
