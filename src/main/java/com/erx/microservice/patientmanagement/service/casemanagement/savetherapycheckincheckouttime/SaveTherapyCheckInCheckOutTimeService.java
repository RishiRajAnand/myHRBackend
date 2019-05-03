package com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime;

/*
* created by raushan on 25-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveTherapyCheckInCheckOutTimeService {

    SaveTherapyCheckInCheckOutTimeServiceResponse execute(SaveTherapyCheckInCheckOutTimeServiceRequest request) throws ServiceException;

}
