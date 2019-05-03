package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientRefundByClinicLocationService {

    GetPatientRefundByClinicLocationServiceResponse execute(GetPatientRefundByClinicLocationServiceRequest request) throws ServiceException;
}
