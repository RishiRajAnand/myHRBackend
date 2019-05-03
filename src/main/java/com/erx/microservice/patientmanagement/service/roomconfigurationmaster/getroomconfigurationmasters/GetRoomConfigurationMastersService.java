package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetRoomConfigurationMastersService {

    GetRoomConfigurationMastersServiceResponse execute(GetRoomConfigurationMastersServiceRequest request) throws ServiceException;
}
