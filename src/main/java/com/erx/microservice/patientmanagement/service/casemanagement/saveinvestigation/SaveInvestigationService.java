package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation;

/*
* created by Latha on 17-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveInvestigationService {

    SaveInvestigationServiceResponse execute(SaveInvestigationServiceRequest request) throws ServiceException;
}
