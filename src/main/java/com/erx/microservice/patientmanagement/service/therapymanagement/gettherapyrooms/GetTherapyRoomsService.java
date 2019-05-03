package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms;

/*
* created by Latha on 07-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyRoomsService {

    GetTherapyRoomsServiceResponse execute(GetTherapyRoomsServiceRequest request)throws ServiceException;
}
