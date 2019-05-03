package com.erx.microservice.patientmanagement.service.dischargerequest.savedischargerequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface SaveDischargeRequestService {

    SaveDischargeRequestServiceResponse execute(SaveDischargeRequestServiceRequest request)throws ServiceException;
}
