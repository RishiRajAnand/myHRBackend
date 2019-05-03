package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid;

/*
* created by Brighty on 16-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetBedConfigurationMasterByIdService {

    GetBedConfigurationMasterByIdServiceResponse execute(GetBedConfigurationMasterByIdServiceRequest request) throws ServiceException;
}
