package com.erx.microservice.patientmanagement.service.ipadmission.allocatebed;

/*
* created by Latha on 29-11-2017
* */

import org.hibernate.service.spi.ServiceException;

public interface AllocateBedService {

    AllocateBedServiceResponse execute(AllocateBedServiceRequest request) throws ServiceException;
}
