package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster;

/*
* created by Latha on 04-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyMasterService {

    GetTherapyMasterServiceResponse execute(GetTherapyMasterServiceRequest request)throws ServiceException;
}
