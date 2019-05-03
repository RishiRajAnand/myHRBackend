package com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreateBedTypeMasterService {

    CreateBedTypeMasterServiceResponse execute(CreateBedTypeMasterServiceRequest request) throws ServiceException;
}
