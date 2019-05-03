package com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 27-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetMedicineTreatmentByCaseIdService {

    GetMedicineTreatmentByCaseIdServiceResponse execute(GetMedicineTreatmentByCaseIdServiceRequest request) throws ServiceException;
}
