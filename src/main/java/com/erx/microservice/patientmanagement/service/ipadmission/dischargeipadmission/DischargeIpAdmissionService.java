package com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission;

/*
* created by Brighty on Brighty on 21-06-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DischargeIpAdmissionService {

    DischargeIpAdmissionServiceResponse execute(DischargeIpAdmissionServiceRequest request) throws ServiceException;
}
