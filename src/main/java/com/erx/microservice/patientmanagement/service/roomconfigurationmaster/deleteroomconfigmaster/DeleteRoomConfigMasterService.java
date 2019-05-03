package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster;


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeleteRoomConfigMasterService {

    DeleteRoomConfigMasterServiceResponse execute(DeleteRoomConfigMasterServiceRequest request) throws ServiceException;
}
