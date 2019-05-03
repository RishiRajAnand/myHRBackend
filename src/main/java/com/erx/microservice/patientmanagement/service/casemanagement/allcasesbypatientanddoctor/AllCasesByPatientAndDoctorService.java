package com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor;

/*
* created by Latha on 18-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface AllCasesByPatientAndDoctorService {

    AllCasesByPatientAndDoctorServiceResponse execute(AllCasesByPatientAndDoctorServiceRequest request) throws ServiceException;
}
