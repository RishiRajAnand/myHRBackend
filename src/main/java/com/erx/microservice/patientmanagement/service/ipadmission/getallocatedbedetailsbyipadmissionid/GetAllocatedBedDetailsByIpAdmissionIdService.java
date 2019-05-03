package com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid;

/*
* created by Raushan on 06-10-2018.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetAllocatedBedDetailsByIpAdmissionIdService {

    GetAllocatedBedDetailsByIpAdmissionIdServiceResponse execute(GetAllocatedBedDetailsByIpAdmissionIdServiceRequest request) throws ServiceException;
}
