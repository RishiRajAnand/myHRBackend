package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines;

/*
* created by Latha on 31-08-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface GetBkdGroupMedicineService {

    GetBkdGroupMedicineServiceResponse execute(GetBkdGroupMedicineServiceRequest request)throws ServiceException;
}
