package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreateRoomConfigurationMasterService {

    CreateRoomConfigurationMasterServiceResponse execute(CreateRoomConfigurationMasterServiceRequest request) throws ServiceException;
}
