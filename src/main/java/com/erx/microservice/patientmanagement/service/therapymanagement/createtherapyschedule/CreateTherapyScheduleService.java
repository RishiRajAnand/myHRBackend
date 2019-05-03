package com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule;

/*
* created by Raushan on 27-11-2018
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreateTherapyScheduleService {

    CreateTherapyScheduleServiceResponse execute(CreateTherapyScheduleServiceRequest request) throws ServiceException;

}
