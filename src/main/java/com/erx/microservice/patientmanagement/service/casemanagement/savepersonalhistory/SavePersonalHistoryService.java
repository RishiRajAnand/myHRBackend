package com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory;

/*
* created by Latha on 21-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SavePersonalHistoryService {

    SavePersonalHistoryServiceResponse execute(SavePersonalHistoryServiceRequest request) throws ServiceException;
}
