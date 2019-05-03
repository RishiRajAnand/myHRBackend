package com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreatePatientRefundService {

    CreatePatientRefundServiceResponse execute(CreatePatientRefundServiceRequest request) throws ServiceException;
}
