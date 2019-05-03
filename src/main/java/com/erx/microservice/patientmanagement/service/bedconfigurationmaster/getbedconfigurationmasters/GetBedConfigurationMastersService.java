package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters;

/*
* created by Brighty on 20-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetBedConfigurationMastersService {

    GetBedConfigurationMastersServiceResponse execute(GetBedConfigurationMastersServiceRequest request) throws ServiceException;
}
