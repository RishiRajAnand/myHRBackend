package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

/*
* Created by latha on 06/12/18.
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class DischargeRequestTrackerRequest implements CommonServiceRequest {

    private PatientSearchCriteria patientSearchCriteria;

    private Pageable pageable;

    //Constructor

    public DischargeRequestTrackerRequest(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        this.patientSearchCriteria = patientSearchCriteria;
        this.pageable = pageable;
    }

    public DischargeRequestTrackerRequest() {
    }

    //Getters and setters

    public PatientSearchCriteria getPatientSearchCriteria() {
        return patientSearchCriteria;
    }

    public void setPatientSearchCriteria(PatientSearchCriteria patientSearchCriteria) {
        this.patientSearchCriteria = patientSearchCriteria;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
