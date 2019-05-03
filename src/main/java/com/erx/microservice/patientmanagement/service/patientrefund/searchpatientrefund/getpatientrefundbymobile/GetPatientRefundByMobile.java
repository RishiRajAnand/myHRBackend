package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbymobile;

import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

/*
* created by Raushan on 14-02-18
* */


public interface GetPatientRefundByMobile {

    CommonServiceResponse execute(CommonServiceRequest serviceRequest) throws ServiceException;
}
