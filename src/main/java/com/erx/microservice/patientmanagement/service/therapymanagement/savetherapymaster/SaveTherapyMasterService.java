package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster;

/*
* created by Latha on 07-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveTherapyMasterService {

    SaveTherapyMasterServiceResponse execute(SaveTherapyMasterServiceRequest request) throws ServiceException;
}
