package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetRoomConfigurationMasterByIdService {

    GetRoomConfigurationMasterByIdServiceResponse execute(GetRoomConfigurationMasterByIdServiceRequest request) throws ServiceException;
}
