package com.erx.microservice.patientmanagement.service.patientsearch;

/**
 * Created by mkpatil on 28/12/17.
 */
public interface NewPatientSearchService {

    PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception;

}
