package com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicLocationDetail;
import org.hibernate.service.spi.ServiceException;

public interface SaveComplaintService {

    SaveComplaintServiceResponse execute(SaveComplaintServiceRequest request) throws ServiceException;

    String generateCaseNumberByClinicLocation(ClinicLocationDetail clinicLocationDetail);
}
