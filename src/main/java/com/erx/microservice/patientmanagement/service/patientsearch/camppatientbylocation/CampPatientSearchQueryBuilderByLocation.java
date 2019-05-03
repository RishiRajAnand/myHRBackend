package com.erx.microservice.patientmanagement.service.patientsearch.camppatientbylocation;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;

import java.util.List;

/**
 * Created by Latha on 05/01/18.
 */

public interface CampPatientSearchQueryBuilderByLocation {

    List<CampRegistrationDTO> getCampPatientSearchResults(PatientSearchCriteria patientSearchCriteria);
}
