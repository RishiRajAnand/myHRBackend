package com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetAllAdmittedPatientByClinicLocationService {

    GetAllAdmittedPatientByClinicLocationServiceResponse execute
            (GetAllAdmittedPatientByClinicLocationServiceRequest request) throws ServiceException;
}
