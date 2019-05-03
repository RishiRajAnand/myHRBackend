package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment;

/*
* created by Brighty on 04-01-2017
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetWardMastersByDepartmentService {

    GetWardMastersByDepartmentServiceResponse execute(GetWardMastersByDepartmentServiceRequest request)throws ServiceException;
}
