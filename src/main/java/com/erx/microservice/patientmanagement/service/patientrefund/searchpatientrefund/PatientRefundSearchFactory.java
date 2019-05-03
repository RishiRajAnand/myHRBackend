package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;

import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public interface PatientRefundSearchFactory {

    CommonServiceResponse execute(SearchPatientRefundServiceRequest request) throws ServiceException;
}
