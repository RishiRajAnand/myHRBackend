package com.erx.microservice.patientmanagement.service.iprequest.saveipadmissionrequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface SaveIpAdmissionRequestService {

    SaveIpAdmissionRequestServiceResponse execute(SaveIpAdmissionRequestServiceRequest request)throws ServiceException;
}
