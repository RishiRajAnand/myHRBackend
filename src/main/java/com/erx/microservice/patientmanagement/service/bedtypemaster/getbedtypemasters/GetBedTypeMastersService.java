package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters;

/*
* created by Brighty on 16-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetBedTypeMastersService {

    GetBedTypeMastersServiceResponse execute(GetBedTypeMastersServiceRequest request) throws ServiceException;

}
