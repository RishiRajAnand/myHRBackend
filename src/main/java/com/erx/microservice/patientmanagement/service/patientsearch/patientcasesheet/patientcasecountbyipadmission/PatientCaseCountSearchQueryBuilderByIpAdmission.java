package com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.patientcasecountbyipadmission;


import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
import org.springframework.data.domain.Page;

/**
 * Created by Latha on 16/08/18.
 */

public interface PatientCaseCountSearchQueryBuilderByIpAdmission {

    Page<PatientCaseCountSearchDTO> getPatientSearchResults(PatientSearchServiceRequest request);
}
