package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate;

/*
* created by Latha on 27-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteMedicineTemplateService {

    DeleteMedicineTemplateServiceResponse execute(DeleteMedicineTemplateServiceRequest request) throws ServiceException;
}
