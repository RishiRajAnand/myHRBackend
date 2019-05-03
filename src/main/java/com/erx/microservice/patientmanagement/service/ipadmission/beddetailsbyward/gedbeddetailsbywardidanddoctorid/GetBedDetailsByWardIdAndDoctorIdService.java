package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardidanddoctorid;

/*
* created by Brighty on 28-04-2018.
* */

import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import org.hibernate.service.spi.ServiceException;

public interface GetBedDetailsByWardIdAndDoctorIdService {

    GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException;
}
