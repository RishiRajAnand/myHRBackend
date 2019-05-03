package com.erx.microservice.patientmanagement.service.dto;

public class DoctorDTO {

    private long doctorID;
    private String doctorName;
    private String doctorDepartmentName;
    private String doctorLocationName;

    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDepartmentName() {
        return doctorDepartmentName;
    }

    public void setDoctorDepartmentName(String doctorDepartmentName) {
        this.doctorDepartmentName = doctorDepartmentName;
    }

    public String getDoctorLocationName() {
        return doctorLocationName;
    }

    public void setDoctorLocationName(String doctorLocationName) {
        this.doctorLocationName = doctorLocationName;
    }
}
