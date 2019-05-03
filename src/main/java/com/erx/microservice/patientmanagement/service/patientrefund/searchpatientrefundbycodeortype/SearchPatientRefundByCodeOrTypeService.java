package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype;
/*
* created by Brighty on 11-01-2017
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface SearchPatientRefundByCodeOrTypeService {

    SearchPatientRefundByCodeOrTypeServiceResponse execute(SearchPatientRefundByCodeOrTypeServiceRequest request) throws ServiceException;
}
