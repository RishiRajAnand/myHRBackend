package com.erx.microservice.patientmanagement.service.patientsearch.patientbynamevisit;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;

import java.util.List;

/**
 * Created by raushan on 14/02/18.
 */
public interface PatientSearchQueryBuilderByNameVisit {

    List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria);
}
