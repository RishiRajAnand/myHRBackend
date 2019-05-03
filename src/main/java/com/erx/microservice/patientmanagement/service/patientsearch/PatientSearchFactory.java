package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientCaseCountSearchDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientSearchFactory {

    public List<PatientSearchDTO> getPatientSearch(PatientSearchCriteria patientSearchCriteria);

    public List<CampRegistrationDTO> getCampPatientSearch(PatientSearchCriteria patientSearchCriteria);

    public Page<PatientCaseCountSearchDTO> getPatientCaseCountSearch(PatientSearchServiceRequest request);
}
