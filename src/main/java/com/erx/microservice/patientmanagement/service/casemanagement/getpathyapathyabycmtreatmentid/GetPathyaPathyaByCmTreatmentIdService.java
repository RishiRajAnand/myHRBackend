package com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid;

/*
* created by Latha on 02-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetPathyaPathyaByCmTreatmentIdService {

    GetPathyaPathyaByCmTreatmentIdServiceResponse execute(GetPathyaPathyaByCmTreatmentIdServiceRequest request) throws ServiceException;
}
