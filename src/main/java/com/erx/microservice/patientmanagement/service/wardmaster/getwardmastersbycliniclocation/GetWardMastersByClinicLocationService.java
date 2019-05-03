package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetWardMastersByClinicLocationService {

    GetWardMastersByClinicLocationServiceResponse execute(GetWardMastersByClinicLocationServiceRequest request) throws ServiceException;
}
