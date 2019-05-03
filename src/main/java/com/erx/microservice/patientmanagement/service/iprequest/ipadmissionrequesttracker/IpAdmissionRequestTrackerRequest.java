package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker;

/*
* created by Brighty on 11-06-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class IpAdmissionRequestTrackerRequest implements CommonServiceRequest {

    private PatientSearchCriteria patientSearchCriteria;

    private Pageable pageable;

    //Constructor

    public IpAdmissionRequestTrackerRequest(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        this.patientSearchCriteria = patientSearchCriteria;
        this.pageable = pageable;
    }

    public IpAdmissionRequestTrackerRequest() {
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
