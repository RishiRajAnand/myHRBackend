package com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetExaminationByCaseIdService {

    GetExaminationByCaseIdServiceResponse execute(GetExaminationByCaseIdServiceRequest request) throws ServiceException;

}
