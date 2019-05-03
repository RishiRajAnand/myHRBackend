package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by mkpatil on 28/12/17.
 */
public class PatientSearchServiceRequest implements CommonServiceRequest {

    PatientSearchCriteria patientSearchCriteria;
    private Pageable pageable;

    public PatientSearchServiceRequest(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        this.patientSearchCriteria = patientSearchCriteria;
        this.pageable = pageable;
    }

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
