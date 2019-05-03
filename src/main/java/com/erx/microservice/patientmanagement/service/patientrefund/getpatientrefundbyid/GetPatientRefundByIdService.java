package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientRefundByIdService {

    GetPatientRefundByIdServiceResponse execute(GetPatientRefundByIdServiceRequest request) throws ServiceException;
}
