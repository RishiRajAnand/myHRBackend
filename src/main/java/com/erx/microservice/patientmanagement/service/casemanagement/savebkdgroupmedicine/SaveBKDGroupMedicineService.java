package com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine;

/*
* created by Latha on 26-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveBKDGroupMedicineService {

    SaveBKDGroupMedicineServiceResponse execute(SaveBKDGroupMedicineServiceRequest request) throws ServiceException;
}
