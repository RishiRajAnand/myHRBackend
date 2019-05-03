package com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid;

/*
* created by raushan on 28-08-2018
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetCaseIdByOrderIdService {

    GetCaseIdByOrderIdServiceResponse execute(GetCaseIdByOrderIdServiceRequest request) throws ServiceException;

}
