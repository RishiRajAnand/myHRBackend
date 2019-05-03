package com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange;

/*
* created by Brighty on 08-12-17
* */

import org.hibernate.service.spi.ServiceException;

public interface CampRegistrationSearchByDateRangeService {

    CampRegistrationSearchByDateRangeServiceResponse execute(CampRegistrationSearchByDateRangeServiceRequest request) throws ServiceException;
}
