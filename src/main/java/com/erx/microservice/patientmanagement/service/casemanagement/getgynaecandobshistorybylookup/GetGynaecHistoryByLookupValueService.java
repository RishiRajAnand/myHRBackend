package com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup;

/*
* created by Latha on 20-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetGynaecHistoryByLookupValueService {

    GetGynaecHistoryByLookupValueServiceResponse execute(GetGynaecHistoryByLookupValueServiceRequest request) throws ServiceException;
}
