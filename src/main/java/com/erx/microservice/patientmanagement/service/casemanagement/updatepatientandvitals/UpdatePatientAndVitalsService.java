package com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals;

/*
* created by Latha on 18-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface UpdatePatientAndVitalsService {

    UpdatePatientAndVitalsServiceResponse execute(UpdatePatientAndVitalsServiceRequest request) throws ServiceException;

}
