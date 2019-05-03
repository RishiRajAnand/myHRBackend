package com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation;

/*
* created by Brighty on 13-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetAllActivePatientTypesByClinicLocationService {

    GetAllActivePatientTypesByClinicLocationServiceResponse execute(
            GetAllActivePatientTypesByClinicLocationServiceRequest request) throws ServiceException;
}
