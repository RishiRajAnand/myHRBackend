package com.erx.microservice.patientmanagement.service.patientsearch.camppatientbyname;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;

import java.util.List;

/**
 * Created by Raushan on 16/02/18.
 */

public interface CampPatientSearchQueryBuilderByName {

    List<CampRegistrationDTO> getCampPatientSearchResults(PatientSearchCriteria patientSearchCriteria);
}
