package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyTemplateService {

    GetTherapyTemplateServiceResponse execute(GetTherapyTemplateServiceRequest request)throws ServiceException;
}
