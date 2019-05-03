package com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor;

/*
* created by Brighty on 02-07-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetLastAppointmentByPatientAndDoctorServiceRequest implements CommonServiceRequest{

    private Long patientId;

    private Long doctorId;

    //Constructor

    public GetLastAppointmentByPatientAndDoctorServiceRequest(Long patientId, Long doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public GetLastAppointmentByPatientAndDoctorServiceRequest() {
    }

    //Getters and setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
