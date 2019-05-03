package com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment;

/*
* created by Latha on 21-04-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetServicesByPatientAppointmentRequest implements CommonServiceRequest {

    private Long patientAppointmentID;

    //constructor
    public GetServicesByPatientAppointmentRequest(Long patientAppointmentID) {
        this.patientAppointmentID = patientAppointmentID;
    }

    //getters and setters
    public Long getPatientAppointmentID() {
        return patientAppointmentID;
    }

    public void setPatientAppointmentID(Long patientAppointmentID) {
        this.patientAppointmentID = patientAppointmentID;
    }
}
