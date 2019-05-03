package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare;

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetAllBedTypeMastersByIsDayCareService {

    GetAllBedTypeMastersByIsDayCareServiceResponse execute(GetAllBedTypeMastersByIsDayCareServiceRequest request) throws ServiceException;

}
