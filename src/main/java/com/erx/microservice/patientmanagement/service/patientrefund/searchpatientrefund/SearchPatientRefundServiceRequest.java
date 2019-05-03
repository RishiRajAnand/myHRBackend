package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;
/*
* created by Raushan on 13-02-18
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class SearchPatientRefundServiceRequest implements CommonServiceRequest {

    private PatientSearchCriteria patientSearchCriteria;

    //constructor
    public SearchPatientRefundServiceRequest(PatientSearchCriteria patientSearchCriteria) {
        this.patientSearchCriteria = patientSearchCriteria;
    }

    //getters and setters
    public PatientSearchCriteria getPatientSearchCriteria() {
        return patientSearchCriteria;
    }

    public void setPatientSearchCriteria(PatientSearchCriteria patientSearchCriteria) {
        this.patientSearchCriteria = patientSearchCriteria;
    }
}
