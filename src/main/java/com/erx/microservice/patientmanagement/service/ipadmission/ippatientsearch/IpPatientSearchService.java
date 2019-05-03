package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch;

/*
* created by Brighty on 30-11-17
* */

import org.hibernate.service.spi.ServiceException;

public interface IpPatientSearchService {

    IpPatientSearchServiceResponse execute(IpPatientSearchServiceRequest request) throws ServiceException;
}
