package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetBedConfigurationMastersByClinicLocationService {

    GetBedConfigurationMastersByClinicLocationServiceResponse execute(GetBedConfigurationMastersByClinicLocationServiceRequest request) throws ServiceException;
}
