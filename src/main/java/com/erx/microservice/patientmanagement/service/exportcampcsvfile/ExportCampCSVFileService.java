/*
* created by Raushan on 15-02-2018
* */
package com.erx.microservice.patientmanagement.service.exportcampcsvfile;


import com.erx.microservice.patientmanagement.exception.ServiceException;
import java.io.File;

public interface ExportCampCSVFileService {
     File createFile(Long clinicId ) throws ServiceException;
     ExportCampCSVFileServiceResponse execute(ExportCampCSVFileServiceRequest request) throws ServiceException;
}
