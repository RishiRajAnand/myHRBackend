package com.erx.microservice.patientmanagement.service.ipadmission.patientsearch;

/*
* created by Latha on 06-12-17
* */

import org.hibernate.service.spi.ServiceException;

public interface PatientSearchService {

    PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws ServiceException;
}
