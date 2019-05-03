package com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid;

/*
* created by Latha on 05-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetDischargeDetailsByCaseIdService {

    GetDischargeDetailsByCaseIdServiceResponse execute(GetDischargeDetailsByCaseIdServiceRequest request) throws ServiceException;

}
