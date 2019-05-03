package com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype;

/*
* created by raushan on 30-05-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientAppointmentByIdAndVisitTypeServiceRequest implements CommonServiceRequest {

    private Long patientAppointmentID;
    private String visitType;

    //constructor


    public GetPatientAppointmentByIdAndVisitTypeServiceRequest(Long patientAppointmentID, String visitType) {
        this.patientAppointmentID = patientAppointmentID;
        this.visitType = visitType;
    }

    //getters and setters
    public Long getPatientAppointmentID() {
        return patientAppointmentID;
    }

    public void setPatientAppointmentID(Long patientAppointmentID) {
        this.patientAppointmentID = patientAppointmentID;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }
}
