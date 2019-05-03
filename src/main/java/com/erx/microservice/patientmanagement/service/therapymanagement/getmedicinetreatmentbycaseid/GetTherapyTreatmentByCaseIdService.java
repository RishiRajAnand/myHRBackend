package com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 11-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyTreatmentByCaseIdService {

    GetTherapyTreatmentByCaseIdServiceResponse execute(GetTherapyTreatmentByCaseIdServiceRequest request) throws ServiceException;
}
