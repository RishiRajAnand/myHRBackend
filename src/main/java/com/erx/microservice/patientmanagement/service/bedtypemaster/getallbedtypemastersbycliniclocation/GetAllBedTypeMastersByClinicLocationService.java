package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetAllBedTypeMastersByClinicLocationService {

    GetAllBedTypeMastersByClinicLocationServiceResponse execute(GetAllBedTypeMastersByClinicLocationServiceRequest request) throws ServiceException;
}
