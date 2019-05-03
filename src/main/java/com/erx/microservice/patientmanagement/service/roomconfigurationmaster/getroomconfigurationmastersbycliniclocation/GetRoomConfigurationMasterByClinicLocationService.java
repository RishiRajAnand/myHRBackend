package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetRoomConfigurationMasterByClinicLocationService {

    GetRoomConfigurationMasterByClinicLocationServiceResponse execute(
            GetRoomConfigurationMasterByClinicLocationServiceRequest request) throws ServiceException;
}
