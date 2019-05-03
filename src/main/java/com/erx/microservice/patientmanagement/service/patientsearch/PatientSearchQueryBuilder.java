package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;

import java.util.List;

/**
 * Created by mkpatil on 28/12/17.
 */
public interface PatientSearchQueryBuilder {

    String PATIENT_DETAIL = "select p, ip,ibm from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "left join ip.ipAdmissionBedMovements ibm with ibm.currentBed = 1  ";

    List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria);
}
