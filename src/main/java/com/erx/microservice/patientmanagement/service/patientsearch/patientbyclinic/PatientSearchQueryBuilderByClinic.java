package com.erx.microservice.patientmanagement.service.patientsearch.patientbyclinic;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;

import java.util.List;

/**
 * Created by Latha on 03/01/18.
 */

public interface PatientSearchQueryBuilderByClinic {
    List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria);
}
