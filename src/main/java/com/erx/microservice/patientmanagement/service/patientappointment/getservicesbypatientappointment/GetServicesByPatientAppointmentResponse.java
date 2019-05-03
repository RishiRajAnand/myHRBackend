package com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment;

/*
* created by Latha on 21-04-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;

public class GetServicesByPatientAppointmentResponse extends CommonServiceResponse {

    private PatientAppointmentDTO patientAppointmentDTO;

    //constructor

    public GetServicesByPatientAppointmentResponse(PatientAppointmentDTO patientAppointmentDTO) {
        this.patientAppointmentDTO = patientAppointmentDTO;
    }

    public GetServicesByPatientAppointmentResponse() {
    }

    //getters and setters

    public PatientAppointmentDTO getPatientAppointmentDTO() {
        return patientAppointmentDTO;
    }

    public void setPatientAppointmentDTO(PatientAppointmentDTO patientAppointmentDTO) {
        this.patientAppointmentDTO = patientAppointmentDTO;
    }
}
