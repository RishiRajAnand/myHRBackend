package com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference;

/*
* created by Latha on 18-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetClinicPreferenceByClinicService {

    GetClinicPreferenceByClinicServiceResponse execute(GetClinicPreferenceByClinicServiceRequest request) throws ServiceException;
}
