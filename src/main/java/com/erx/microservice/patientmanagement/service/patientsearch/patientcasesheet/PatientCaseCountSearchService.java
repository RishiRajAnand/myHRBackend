package com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet;

import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceResponse;

/**
 * Created by Latha on 16/08/18.
 */

public interface PatientCaseCountSearchService {

    PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception;
}
