package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate;

/*
* created by Brighty on 30-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class IpPatientSearchByDateServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    private String startDate;

    private String endDate;

    //getters and setters

    public IpPatientSearchByDateServiceRequest(Long clinicId, String startDate, String endDate) {
        this.clinicId = clinicId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public IpPatientSearchByDateServiceRequest() {
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    //constructor

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
