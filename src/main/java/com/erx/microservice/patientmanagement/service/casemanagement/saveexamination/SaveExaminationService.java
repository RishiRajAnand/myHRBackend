package com.erx.microservice.patientmanagement.service.casemanagement.saveexamination;

/*
* created by Latha on 06-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveExaminationService {

    SaveExaminationServiceResponse execute(SaveExaminationServiceRequest request) throws ServiceException;
}
