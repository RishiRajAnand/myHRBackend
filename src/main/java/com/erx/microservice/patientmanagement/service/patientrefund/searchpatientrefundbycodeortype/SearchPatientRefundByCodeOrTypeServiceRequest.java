package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype;

/*
* created by Brighty on 11-01-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class SearchPatientRefundByCodeOrTypeServiceRequest implements CommonServiceRequest {

    private Long patientId;
    private String searchValue;

    //Getters and setters

    public SearchPatientRefundByCodeOrTypeServiceRequest(Long patientId, String searchValue) {
        this.patientId = patientId;
        this.searchValue = searchValue;
    }

    public SearchPatientRefundByCodeOrTypeServiceRequest() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    //Constructor

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
