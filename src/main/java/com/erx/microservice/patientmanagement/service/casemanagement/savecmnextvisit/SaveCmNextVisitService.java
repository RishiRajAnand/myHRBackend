package com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit;

/*
* created by Latha on 10-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveCmNextVisitService {

    SaveCmNextVisitServiceResponse execute(SaveCmNextVisitServiceRequest request) throws ServiceException;

}
