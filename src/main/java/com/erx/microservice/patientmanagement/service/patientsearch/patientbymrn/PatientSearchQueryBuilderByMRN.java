package com.erx.microservice.patientmanagement.service.patientsearch.patientbymrn;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;

import java.util.List;

/**
 * Created by mkpatil on 28/12/17.
 */
public interface PatientSearchQueryBuilderByMRN {

    List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria);

}
