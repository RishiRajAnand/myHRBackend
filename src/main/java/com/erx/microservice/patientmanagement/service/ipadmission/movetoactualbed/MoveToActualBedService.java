package com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed;

import org.hibernate.service.spi.ServiceException;

/*
* created by Latha on 29-11-2017
* */
public interface MoveToActualBedService {
    MoveToActualBedServiceResponse execute(MoveToActualBedServiceRequest request) throws ServiceException;
}
