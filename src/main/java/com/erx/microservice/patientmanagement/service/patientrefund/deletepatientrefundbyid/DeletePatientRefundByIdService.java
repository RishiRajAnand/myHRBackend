package com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeletePatientRefundByIdService {

    DeletePatientRefundByIdServiceResponse execute(DeletePatientRefundByIdServiceRequest request) throws ServiceException;
}
