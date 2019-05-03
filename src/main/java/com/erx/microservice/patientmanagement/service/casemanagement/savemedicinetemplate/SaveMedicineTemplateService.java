package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate;

/*
* created by Latha on 26-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveMedicineTemplateService {

    SaveMedicineTemplateServiceResponse execute(SaveMedicineTemplateServiceRequest request) throws ServiceException;
}
