package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveTherapyTemplateService {

    SaveTherapyTemplateServiceResponse execute(SaveTherapyTemplateServiceRequest request) throws ServiceException;
}
