package com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster;


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeleteWardMasterService {

    DeleteWardMasterServiceResponse execute(DeleteWardMasterServiceRequest request) throws ServiceException;
}
