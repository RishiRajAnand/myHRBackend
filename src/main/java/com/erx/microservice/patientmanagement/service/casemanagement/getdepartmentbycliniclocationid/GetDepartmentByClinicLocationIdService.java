package com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid;

import com.erx.microservice.patientmanagement.exception.ServiceException;

/*
 * created by Latha on 10-10-2018
 * */

public interface GetDepartmentByClinicLocationIdService {

    GetDepartmentByClinicLocationIdServiceResponse execute(GetDepartmentByClinicLocationIdServiceRequest request) throws ServiceException;

}
