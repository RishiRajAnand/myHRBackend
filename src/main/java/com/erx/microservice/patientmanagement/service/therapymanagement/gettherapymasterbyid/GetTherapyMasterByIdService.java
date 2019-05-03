package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid;

/*
* created by Latha on 08-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyMasterByIdService {

    GetTherapyMasterByIdServiceResponse execute(GetTherapyMasterByIdServiceRequest request)throws ServiceException;
}
