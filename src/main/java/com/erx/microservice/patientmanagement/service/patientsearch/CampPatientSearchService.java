package com.erx.microservice.patientmanagement.service.patientsearch;

/**
 * Created by Latha on 08/01/18.
 */

public interface CampPatientSearchService {

    PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception;
}
