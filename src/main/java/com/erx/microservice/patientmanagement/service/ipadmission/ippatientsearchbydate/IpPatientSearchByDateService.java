package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate;

/*
* created by Brighty on 30-11-17
* */

import org.hibernate.service.spi.ServiceException;

public interface IpPatientSearchByDateService {

    IpPatientSearchByDateServiceResponse execute(IpPatientSearchByDateServiceRequest request) throws ServiceException;
}
