package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate;

/*
* created by Latha on 14-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteTherapyTemplateService {

    DeleteTherapyTemplateServiceResponse execute(DeleteTherapyTemplateServiceRequest request) throws ServiceException;
}
