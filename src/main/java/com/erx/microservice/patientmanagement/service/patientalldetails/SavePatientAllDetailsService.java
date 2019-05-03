package com.erx.microservice.patientmanagement.service.patientalldetails;

import com.erx.microservice.patientmanagement.exception.ServiceException;

/*
 * created by Bahubali on 17-10-2018
 * */
public interface SavePatientAllDetailsService {

    SavePatientAllDetailsServiceResponse execute(SavePatientAllDetailsServiceRequest request) throws ServiceException;
}
