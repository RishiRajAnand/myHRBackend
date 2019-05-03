package com.erx.microservice.patientmanagement.service.importcsvfile;

/*
* created by Brighty on 26-03-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface ImportCampCSVFileService {

    ImportCampCSVFileServiceResponse execute(ImportCampCSVFileServiceRequest request) throws ServiceException;
}
