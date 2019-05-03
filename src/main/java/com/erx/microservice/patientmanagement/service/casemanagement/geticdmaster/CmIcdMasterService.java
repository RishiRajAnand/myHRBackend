package com.erx.microservice.patientmanagement.service.casemanagement.geticdmaster;

/*
* created by Latha on 18-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface CmIcdMasterService {

    CmIcdMasterServiceResponse execute() throws ServiceException;
}
