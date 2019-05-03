package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid;

/*
* created by Raushan on 29-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetTherapyScheduleDetailByTherapyIdService {

    GetTherapyScheduleDetailByTherapyIdServiceResponse execute(GetTherapyScheduleDetailByTherapyIdServiceRequest request)throws ServiceException;
}
