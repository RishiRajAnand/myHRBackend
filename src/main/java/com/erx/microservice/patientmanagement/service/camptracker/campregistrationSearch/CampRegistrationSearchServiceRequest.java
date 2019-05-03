package com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch;

/*
* created by Brighty on 07-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class CampRegistrationSearchServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    private String searchParam;

    private String searchValue;

    //getters and setters

    //constructor
    public CampRegistrationSearchServiceRequest(Long clinicId, String searchParam, String searchValue) {
        this.clinicId = clinicId;
        this.searchParam = searchParam;
        this.searchValue = searchValue;
    }

    public CampRegistrationSearchServiceRequest() {
    }

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

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
