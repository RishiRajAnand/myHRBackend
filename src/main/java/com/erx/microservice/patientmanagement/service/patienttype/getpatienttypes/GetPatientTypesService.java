package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes;

/*
* created by Brighty on 09-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientTypesService {

    GetPatientTypesServiceResponse execute(GetPatientTypesServiceRequest request) throws ServiceException;

}
