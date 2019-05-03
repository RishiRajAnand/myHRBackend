package com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid;

/*
* created by raushan on 18-10-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetIPAdmissionNumberByCaseIdService {

    GetIPAdmissionNumberByCaseIdServiceResponse execute(GetIPAdmissionNumberByCaseIdServiceRequest request) throws ServiceException;
}
