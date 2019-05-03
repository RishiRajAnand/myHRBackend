package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteMedicineTreatmentService {

    DeleteMedicineTreatmentServiceResponse execute(DeleteMedicineTreatmentServiceRequest request) throws ServiceException;
}
