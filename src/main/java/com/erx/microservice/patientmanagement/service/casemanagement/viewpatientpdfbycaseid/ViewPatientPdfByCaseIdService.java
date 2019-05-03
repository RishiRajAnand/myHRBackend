package com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid;

/*
* created by Latha on 05-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface ViewPatientPdfByCaseIdService {

    ViewPatientPdfByCaseIdServiceResponse execute(ViewPatientPdfByCaseIdServiceRequest request) throws ServiceException;

}
