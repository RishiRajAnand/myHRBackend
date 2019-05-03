package com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment;
/*
* created by Brighty on 29-11-2017.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetDoctorDepartmentService {

    GetDoctorDepartmentServiceResponse execute(GetDoctorDepartmentServiceRequest request) throws ServiceException;
}
