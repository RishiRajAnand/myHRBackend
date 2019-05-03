package com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetPersonalHistoryByCaseIdService {

    GetPersonalHistoryByCaseIdServiceResponse execute(GetPersonalHistoryByCaseIdServiceRequest request) throws ServiceException;

}
