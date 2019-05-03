package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.gedbeddetailsbywardid;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import org.hibernate.service.spi.ServiceException;

public interface GetBedDetailsByWardIdService {

    GetBedDetailsByWardIdServiceResponse execute(GetBedDetailsByWardIdServiceRequest request) throws ServiceException;
}
