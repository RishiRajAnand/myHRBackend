package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetBedTypeMasterByIdService {

    GetBedTypeMasterByIdServiceResponse execute(GetBedTypeMasterByIdServiceRequest request) throws ServiceException;
}
