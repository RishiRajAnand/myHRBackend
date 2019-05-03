package com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies;

/*
* created by Latha on 27-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetNonConfigureTherapiesService {

    GetNonConfigureTherapiesServiceResponse execute(GetNonConfigureTherapiesServiceRequest request)throws ServiceException;
}
