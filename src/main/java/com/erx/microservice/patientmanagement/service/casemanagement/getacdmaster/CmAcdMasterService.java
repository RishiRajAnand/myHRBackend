package com.erx.microservice.patientmanagement.service.casemanagement.getacdmaster;

/*
* created by Latha on 18-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface CmAcdMasterService {

    CmAcdMasterServiceResponse execute() throws ServiceException;
}
