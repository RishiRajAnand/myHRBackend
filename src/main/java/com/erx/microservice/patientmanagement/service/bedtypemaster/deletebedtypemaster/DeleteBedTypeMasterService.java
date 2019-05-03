package com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster;

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeleteBedTypeMasterService {

    DeleteBedTypeMasterServiceResponse execute(DeleteBedTypeMasterServiceRequest request) throws ServiceException;
}
