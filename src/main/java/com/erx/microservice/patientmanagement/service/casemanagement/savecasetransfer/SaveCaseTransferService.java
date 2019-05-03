package com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer;

/*
* created by Latha on 10-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveCaseTransferService {

    SaveCaseTransferServiceResponse execute(SaveCaseTransferServiceRequest request) throws ServiceException;

}
