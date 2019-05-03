package com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid;

/*
* created by Raushan on 05-10-2018.
* */


import org.hibernate.service.spi.ServiceException;

public interface GetBedTypeIdForSchedulerByIpAdmissionIdService {
    GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse execute(GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest request) throws ServiceException;
}
