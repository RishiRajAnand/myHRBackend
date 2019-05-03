package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid;

/*
* created by Latha on 31-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetBkdGroupMedicineByIdService {

    GetBkdGroupMedicineByIdServiceResponse execute(GetBkdGroupMedicineByIdServiceRequest request)throws ServiceException;
}
