package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails;

/*
* created by Latha on 01-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveInvestigationAndTreatmentDetailsService {

    SaveInvestigationAndTreatmentDetailsServiceResponse execute(SaveInvestigationAndTreatmentDetailsServiceRequest request) throws ServiceException;
}
