package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid;

/*
* created by Brighty on 13-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientTypeByIdService {

    GetPatientTypeByIdServiceResponse execute(GetPatientTypeByIdServiceRequest request) throws ServiceException;
}
