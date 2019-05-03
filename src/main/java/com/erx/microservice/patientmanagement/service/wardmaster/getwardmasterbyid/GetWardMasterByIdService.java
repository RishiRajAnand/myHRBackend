package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetWardMasterByIdService {

    GetWardMasterByIdServiceResponse execute(GetWardMasterByIdServiceRequest request) throws ServiceException;
}
