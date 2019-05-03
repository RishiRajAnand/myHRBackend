package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosageinstruction;

/*
* created by Latha on 24-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetCmDosageInstructionService {

    GetCmDosageInstructionServiceResponse execute() throws ServiceException;
}
