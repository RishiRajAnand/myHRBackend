package com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid;

/*
* created by Raushan on 25-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyPlanningIdsByOrderIdService {

    GetTherapyPlanningIdsByOrderIdServiceResponse execute(GetTherapyPlanningIdsByOrderIdServiceRequest  request) throws ServiceException;
}
