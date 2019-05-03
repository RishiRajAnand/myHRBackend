package com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment;

import org.hibernate.service.spi.ServiceException;

/*
* created by Latha on 29-11-2017
* */
public interface BedMovementDepartmentService {
    BedMovementDepartmentServiceResponse execute(BedMovementDepartmentServiceRequest request) throws ServiceException;
}
