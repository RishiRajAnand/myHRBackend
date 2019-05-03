package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster;

/*
* created by Latha on 10-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteTherapyMasterService {

    DeleteTherapyMasterServiceResponse execute(DeleteTherapyMasterServiceRequest request) throws ServiceException;
}
