package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbyname;



/*
* created by Raushan on 14-02-18
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public interface GetPatientRefundByName {
    CommonServiceResponse execute(CommonServiceRequest serviceRequest) throws ServiceException;
}


