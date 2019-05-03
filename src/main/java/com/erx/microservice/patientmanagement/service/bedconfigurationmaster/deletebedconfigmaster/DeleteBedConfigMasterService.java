package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster;

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeleteBedConfigMasterService {

    DeleteBedConfigMasterServiceResponse execute(DeleteBedConfigMasterServiceRequest request) throws ServiceException;
}
