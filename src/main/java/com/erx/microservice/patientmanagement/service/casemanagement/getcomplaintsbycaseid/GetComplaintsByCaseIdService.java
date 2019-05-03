package com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetComplaintsByCaseIdService {

    GetComplaintsByCaseIdServiceResponse execute(GetComplaintsByCaseIdServiceRequest request) throws ServiceException;

}
