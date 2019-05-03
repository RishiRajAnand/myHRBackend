package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid;
/*
* created by Raushan on 06-02-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientRefundByPatientIdAndVisitIdService {

    GetPatientRefundByPatientIdAndVisitIdServiceResponse execute(GetPatientRefundByPatientIdAndVisitIdServiceRequest request) throws ServiceException;
}
