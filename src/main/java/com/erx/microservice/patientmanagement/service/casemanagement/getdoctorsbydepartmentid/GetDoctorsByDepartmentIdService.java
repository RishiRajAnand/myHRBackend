package com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetDoctorsByDepartmentIdService {

    GetDoctorsByDepartmentIdServiceResponse execute(GetDoctorsByDepartmentIdServiceRequest request) throws ServiceException;

}
