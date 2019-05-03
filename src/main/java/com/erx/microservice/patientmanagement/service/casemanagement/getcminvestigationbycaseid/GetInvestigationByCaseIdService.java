package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid;

/*
* created by Latha on 18-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetInvestigationByCaseIdService {

    GetInvestigationByCaseIdServiceResponse execute(GetInvestigationByCaseIdServiceRequest request) throws ServiceException;
}
