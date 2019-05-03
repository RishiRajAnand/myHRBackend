package com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetGynaecHistoryByLookupValueServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long lookupValueId;

    //constructor

    public GetGynaecHistoryByLookupValueServiceRequest(Long clinicId, Long lookupValueId) {
        this.clinicId = clinicId;
        this.lookupValueId = lookupValueId;
    }

    public GetGynaecHistoryByLookupValueServiceRequest() {
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getLookupValueId() {
        return lookupValueId;
    }

    public void setLookupValueId(Long lookupValueId) {
        this.lookupValueId = lookupValueId;
    }
}
