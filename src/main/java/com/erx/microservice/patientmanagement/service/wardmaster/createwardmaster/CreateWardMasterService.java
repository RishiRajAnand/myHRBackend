package com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreateWardMasterService {

    CreateWardMasterServiceResponse execute(CreateWardMasterServiceRequest request) throws ServiceException;
}
