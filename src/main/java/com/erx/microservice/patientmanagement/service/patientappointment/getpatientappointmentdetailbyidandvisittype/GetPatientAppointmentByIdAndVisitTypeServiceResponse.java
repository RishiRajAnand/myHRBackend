package com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype;

/*
* created by raushan on 30-05-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;

public class GetPatientAppointmentByIdAndVisitTypeServiceResponse extends CommonServiceResponse {

    private PatientAppointmentDTO patientAppointmentDTO;

    //constructor

    public GetPatientAppointmentByIdAndVisitTypeServiceResponse(PatientAppointmentDTO patientAppointmentDTO) {
        this.patientAppointmentDTO = patientAppointmentDTO;
    }

    public GetPatientAppointmentByIdAndVisitTypeServiceResponse() {
    }

    //getters and setters

    public PatientAppointmentDTO getPatientAppointmentDTO() {
        return patientAppointmentDTO;
    }

    public void setPatientAppointmentDTO(PatientAppointmentDTO patientAppointmentDTO) {
        this.patientAppointmentDTO = patientAppointmentDTO;
    }
}
