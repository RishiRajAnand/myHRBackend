package com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch;

/*
* created by Brighty on 07-12-17
* */

import org.hibernate.service.spi.ServiceException;

public interface CampRegistrationSearchService {

    CampRegistrationSearchServiceResponse execute(CampRegistrationSearchServiceRequest request) throws ServiceException;
}
