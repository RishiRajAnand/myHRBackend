package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice;

/*
* created by Brighty on 10-01-18
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetWardMastersByDaycareService {

    GetWardMastersByDaycareServiceResponse execute(GetWardMastersByDaycareServiceRequest request)throws ServiceException;
}
