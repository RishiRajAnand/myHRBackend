package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

/**
 * Created by latha on 06/12/18.
 */
public interface DischargeRequestTrackerService {

    DischargeRequestTrackerResponse execute(DischargeRequestTrackerRequest request) throws Exception;

}
