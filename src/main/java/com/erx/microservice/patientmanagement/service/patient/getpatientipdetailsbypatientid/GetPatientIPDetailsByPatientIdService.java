package com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetPatientIPDetailsByPatientIdService {

    GetPatientIPDetailsByPatientIdServiceResponse execute
            (GetPatientIPDetailsByPatientIdServiceRequest request) throws ServiceException;
}
