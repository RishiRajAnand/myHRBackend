package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveMedicineTreatmentService {

    SaveMedicineTreatmentServiceResponse execute(SaveMedicineTreatmentServiceRequest request) throws ServiceException;
}
