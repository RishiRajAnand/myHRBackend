package com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetUserDepartmentByUserIdService {

    GetUserDepartmentByUserIdServiceResponse execute(GetUserDepartmentByUserIdServiceRequest request) throws ServiceException;

}
