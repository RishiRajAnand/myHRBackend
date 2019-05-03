package com.erx.microservice.patientmanagement.service.patienttype.createpatienttype;

/*
* created by Brighty on 13-11-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface CreatePatientTypeService {

    CreatePatientTypeServiceResponse execute(CreatePatientTypeServiceRequest request) throws ServiceException;

    public CreatePatientTypeServiceResponse validatePatientTypeName(String patientTypeName, Long clinicLocationId) throws ServiceException;
}
