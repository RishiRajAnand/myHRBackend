package com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.DoctorDTO;
import java.util.List;

public class GetDoctorsByDepartmentIdServiceResponse extends CommonServiceResponse {

    private List<DoctorDTO> doctorDTOs;

    //constructor
    public GetDoctorsByDepartmentIdServiceResponse() {
    }

    public GetDoctorsByDepartmentIdServiceResponse(List<DoctorDTO> doctorDTOs) {
        this.doctorDTOs = doctorDTOs;
    }

    //setters and getters

    public List<DoctorDTO> getDoctorDTOs() {
        return doctorDTOs;
    }

    public void setDoctorDTOs(List<DoctorDTO> doctorDTOs) {
        this.doctorDTOs = doctorDTOs;
    }
}
