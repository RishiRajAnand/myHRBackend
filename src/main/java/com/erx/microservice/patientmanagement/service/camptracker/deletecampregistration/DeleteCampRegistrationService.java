package com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration;

/*
* created by Brighty on 08-12-17
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteCampRegistrationService {

    DeleteCampRegistrationServiceResponse execute(DeleteCampRegistrationServiceRequest request) throws ServiceException;
}
