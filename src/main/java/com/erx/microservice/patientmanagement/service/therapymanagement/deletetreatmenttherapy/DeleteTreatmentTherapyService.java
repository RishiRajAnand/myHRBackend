package com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy;

/*
* created by Latha on 11-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteTreatmentTherapyService {

    DeleteTreatmentTherapyServiceResponse execute(DeleteTreatmentTherapyServiceRequest request) throws ServiceException;
}
