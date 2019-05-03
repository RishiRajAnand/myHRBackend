package com.erx.microservice.patientmanagement.service.ipadmission.bedmovement;

import org.hibernate.service.spi.ServiceException;

/*
* created by Latha on 29-11-2017
* */
public interface BedMovementService {
    BedMovementServiceResponse execute(BedMovementServiceRequest request) throws ServiceException;
}
