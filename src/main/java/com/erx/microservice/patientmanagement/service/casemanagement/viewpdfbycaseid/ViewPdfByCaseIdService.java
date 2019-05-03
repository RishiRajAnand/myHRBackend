package com.erx.microservice.patientmanagement.service.casemanagement.viewpdfbycaseid;

/*
* created by Latha on 05-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface ViewPdfByCaseIdService {

    ViewPdfByCaseIdServiceResponse execute(ViewPdfByCaseIdServiceRequest request) throws ServiceException;

}
