package com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine;

/*
* created by Latha on 02-09-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface DeleteBkdGroupMedicineService {

    DeleteBkdGroupMedicineServiceResponse execute(DeleteBkdGroupMedicineServiceRequest request) throws ServiceException;
}
