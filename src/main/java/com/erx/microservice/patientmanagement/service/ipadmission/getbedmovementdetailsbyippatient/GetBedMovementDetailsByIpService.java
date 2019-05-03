package com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetBedMovementDetailsByIpService {
    GetBedMovementDetailsByIpServiceResponse execute(GetBedMovementDetailsByIpServiceRequest request) throws ServiceException;
}
