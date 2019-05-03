package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment;

/*
* created by Latha on 04-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveTherapyTreatmentService {

    SaveTherapyTreatmentServiceResponse execute(SaveTherapyTreatmentServiceRequest request) throws ServiceException;

}
