package com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype;


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface DeletePatientTypeService {

    DeletePatientTypeServiceResponse execute(DeletePatientTypeServiceRequest request)throws ServiceException;
}
