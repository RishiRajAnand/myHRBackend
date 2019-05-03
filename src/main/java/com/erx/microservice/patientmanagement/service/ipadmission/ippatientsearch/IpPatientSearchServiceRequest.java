package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch;

/*
* created by Brighty on 30-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class IpPatientSearchServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    private String searchParam;

    private String searchValue;

    private Pageable pageable;

    //Constructor

    public IpPatientSearchServiceRequest(Long clinicId, String searchParam, String searchValue, Pageable pageable) {
        this.clinicId = clinicId;
        this.searchParam = searchParam;
        this.searchValue = searchValue;
        this.pageable = pageable;
    }

    public IpPatientSearchServiceRequest() {
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

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
