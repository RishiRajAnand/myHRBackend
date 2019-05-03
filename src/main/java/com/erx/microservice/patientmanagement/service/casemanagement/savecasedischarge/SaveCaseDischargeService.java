package com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveCaseDischargeService {

    SaveCaseDischargeServiceResponse execute(SaveCaseDischargeServiceRequest request) throws ServiceException;

}
