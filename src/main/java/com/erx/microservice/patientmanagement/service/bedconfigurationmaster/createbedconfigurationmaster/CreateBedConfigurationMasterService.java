package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreateBedConfigurationMasterService {

    CreateBedConfigurationMasterServiceResponse execute(CreateBedConfigurationMasterServiceRequest request)throws ServiceException;
}
