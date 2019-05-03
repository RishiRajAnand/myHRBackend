package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid;

/*
* created by Latha on 12-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyTemplateByIdService {

    GetTherapyTemplateByIdServiceResponse execute(GetTherapyTemplateByIdServiceRequest request)throws ServiceException;
}
