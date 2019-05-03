package com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange;

/*
* created by Brighty on 08-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class CampRegistrationSearchByDateRangeServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    private String startDate;

    private String endDate;

    //getters and setters

    public CampRegistrationSearchByDateRangeServiceRequest(Long clinicId, String startDate, String endDate) {
        this.clinicId = clinicId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CampRegistrationSearchByDateRangeServiceRequest() {
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

    //Constructor

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
