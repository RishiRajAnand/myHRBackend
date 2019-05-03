/*
* created by Latha on 01-10-2018
* */
package com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid;

import org.hibernate.service.spi.ServiceException;

public interface GetItemByOrderIdService {
    GetItemByOrderIdServiceResponse execute(GetItemByOrderIdServiceRequest request) throws ServiceException;
}
