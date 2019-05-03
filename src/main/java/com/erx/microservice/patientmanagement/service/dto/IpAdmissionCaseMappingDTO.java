package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Latha on 29-11-2017
* */
public class IpAdmissionCaseMappingDTO {

    private long Id;
    private IpAdmissionDTO ipAdmission;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public IpAdmissionDTO getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmissionDTO ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

}
