package com.erx.microservice.patientmanagement.service.ipadmission.patientsearch;
/*
* created by Latha on 06-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class PatientSearchServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    private String searchParam;

    private String searchValue;

    public PatientSearchServiceRequest(Long clinicId, String searchParam, String searchValue) {
        this.clinicId = clinicId;
        this.searchParam = searchParam;
        this.searchValue = searchValue;
    }

    public PatientSearchServiceRequest() {
    }

    //getters and setters
    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    //constructor

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
