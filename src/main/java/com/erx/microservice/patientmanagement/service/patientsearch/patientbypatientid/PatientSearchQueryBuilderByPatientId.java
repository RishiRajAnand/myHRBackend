package com.erx.microservice.patientmanagement.service.patientsearch.patientbypatientid;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.domain.VisitTypeMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;

import java.util.List;

public interface PatientSearchQueryBuilderByPatientId {

    List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria);

     VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) throws ServiceException;
}
