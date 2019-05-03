package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid;

/*
* created by Latha on 11-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyMasterByServiceIdService {

    GetTherapyMasterByServiceIdServiceResponse execute(GetTherapyMasterByServiceIdServiceRequest request)throws ServiceException;
}
