package com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic;

import com.erx.microservice.patientmanagement.exception.ServiceException;

/*
 * created by Latha on 10-10-2018
 * */

public interface GetClinicLocationsByClinicService {

    GetClinicLocationsByClinicServiceResponse execute(GetClinicLocationsByClinicServiceRequest request) throws ServiceException;

}
