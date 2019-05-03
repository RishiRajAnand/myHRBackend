package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate;

/*
* created by Latha on 27-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetCmMedicineTemplateService {

    GetCmMedicineTemplateServiceResponse execute(GetCmMedicineTemplateServiceRequest request)throws ServiceException;
}
