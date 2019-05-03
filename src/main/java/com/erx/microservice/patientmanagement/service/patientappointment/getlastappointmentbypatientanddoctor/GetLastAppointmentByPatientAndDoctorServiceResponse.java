package com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor;

/*
* created by Brighty on 02-07-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.GetLastAppointmentByPatientAndDoctorDTO;

public class GetLastAppointmentByPatientAndDoctorServiceResponse extends CommonServiceResponse{

    private GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO;

    //Constructor

    public GetLastAppointmentByPatientAndDoctorServiceResponse
            (GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO) {
        this.getLastAppointmentByPatientAndDoctorDTO = getLastAppointmentByPatientAndDoctorDTO;
    }

    public GetLastAppointmentByPatientAndDoctorServiceResponse() {
    }

    //Getters and setters

    public GetLastAppointmentByPatientAndDoctorDTO getGetLastAppointmentByPatientAndDoctorDTO() {
        return getLastAppointmentByPatientAndDoctorDTO;
    }

    public void setGetLastAppointmentByPatientAndDoctorDTO(GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO) {
        this.getLastAppointmentByPatientAndDoctorDTO = getLastAppointmentByPatientAndDoctorDTO;
    }
}
