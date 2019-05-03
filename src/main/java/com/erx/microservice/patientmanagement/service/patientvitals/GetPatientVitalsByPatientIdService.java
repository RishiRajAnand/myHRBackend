package com.erx.microservice.patientmanagement.service.patientvitals;

/*
* created by Latha on 17-08-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientVitalsByPatientIdService {

    GetPatientVitalsByPatientIdServiceResponse execute(GetPatientVitalsByPatientIdServiceRequest request) throws ServiceException;
}
