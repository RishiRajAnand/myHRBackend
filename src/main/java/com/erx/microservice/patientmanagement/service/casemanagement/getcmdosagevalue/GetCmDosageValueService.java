package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosagevalue;

/*
* created by Latha on 24-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetCmDosageValueService {

    GetCmDosageValueServiceResponse execute() throws ServiceException;
}
