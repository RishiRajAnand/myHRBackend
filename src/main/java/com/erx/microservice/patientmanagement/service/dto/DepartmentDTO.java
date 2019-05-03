package com.erx.microservice.patientmanagement.service.dto;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDTO {

    private long departmentID;
    private String departmentName;
    private List<DoctorDTO> doctorDTOS = new ArrayList<>();

    public long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<DoctorDTO> getDoctorDTOS() {
        return doctorDTOS;
    }

    public void setDoctorDTOS(List<DoctorDTO> doctorDTOS) {
        this.doctorDTOS = doctorDTOS;
    }
}
