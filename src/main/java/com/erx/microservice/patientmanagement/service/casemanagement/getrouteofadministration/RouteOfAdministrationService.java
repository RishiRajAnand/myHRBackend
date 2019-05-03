package com.erx.microservice.patientmanagement.service.casemanagement.getrouteofadministration;

/*
* created by Latha on 24-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface RouteOfAdministrationService {

    RouteOfAdministrationServiceResponse execute() throws ServiceException;
}
