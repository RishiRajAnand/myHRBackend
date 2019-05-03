package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedallbeddetails;

/*
* created by Brighty on 09-05-2018.
* */

import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import org.hibernate.service.spi.ServiceException;

public interface GetAllBedDetailsService {

    GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException;
}
