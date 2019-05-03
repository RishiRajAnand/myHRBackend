package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;

/*
* created by Raushan on 13-02-18
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public interface SearchPatientRefundService {

    CommonServiceResponse execute(SearchPatientRefundServiceRequest request) throws ServiceException;
}
