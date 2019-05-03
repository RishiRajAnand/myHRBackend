package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDateTime;

/**
 * Updated by Brighty on 12-06-2018.
 */

public class IpDischargeDetailDTO {

    private Long ipAdmissionId;
    private String patientName;
    private String mrn;
    private String ipAdmissionNumber;
    private LocalDateTime dateOfAdmission;
    private LocalDateTime dateOfDischarge;
    private String doctorName;
    private String chiefComplaint;
    private String status;

    //Getters and setters

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public LocalDateTime getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDateTime dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
