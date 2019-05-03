package com.erx.microservice.patientmanagement.service.patienttype.getallpatienttypesbycliniclocation;

/*
* created by Brighty on 13-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetAllPatientTypesByClinicLocationService {

    GetAllPatientTypesByClinicLocationServiceResponse execute(
            GetAllPatientTypesByClinicLocationServiceRequest request) throws ServiceException;
}
