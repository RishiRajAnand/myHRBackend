package com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient;

/*
* created by Latha on 29-11-2017.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetCaseDetailsByPatientService {

    GetCaseDetailsByPatientServiceResponse execute(GetCaseDetailsByPatientServiceRequest request) throws ServiceException;
}
