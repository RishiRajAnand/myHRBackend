package com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail;

/*
* created by raushan on 24-10-2018
* */

import org.hibernate.service.spi.ServiceException;

public interface SaveTherapyMedicineDetailService {

    SaveTherapyMedicineDetailServiceResponse execute(SaveTherapyMedicineDetailServiceRequest request) throws ServiceException;

}
