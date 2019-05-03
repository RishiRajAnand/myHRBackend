package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker;

import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceResponse;

/**
 * Created by mkpatil on 28/12/17.
 */
public interface IpAdmissionRequestTrackerService {

    IpAdmissionRequestTrackerResponse execute(IpAdmissionRequestTrackerRequest request) throws Exception;

}
