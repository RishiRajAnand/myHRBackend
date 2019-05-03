package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid;

/*
* created by Latha on 18-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetInvestigationDetailsByCaseIdService {

    GetInvestigationDetailsByCaseIdServiceResponse execute(GetInvestigationDetailsByCaseIdServiceRequest request) throws ServiceException;
}
