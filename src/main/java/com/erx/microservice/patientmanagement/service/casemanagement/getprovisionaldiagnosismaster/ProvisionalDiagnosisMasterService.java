package com.erx.microservice.patientmanagement.service.casemanagement.getprovisionaldiagnosismaster;

/*
* created by Latha on 18-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface ProvisionalDiagnosisMasterService {

    ProvisionalDiagnosisMasterServiceResponse execute() throws ServiceException;
}
