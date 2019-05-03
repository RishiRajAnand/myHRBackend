package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 30-05-2018
* */

import java.time.LocalDateTime;
import java.util.List;

public class PatientDetailDTO {
    private String doctor;

    private Long iPAdmissionId;

    private Long patientId;

    private Long bedId;

    private String visitType;

    private Long visitTypeId;

    private LocalDateTime dateOfAdmission;

    private String visitId;

    private Long bedTypeId;

    private Long departmentId;

    private String patientType;

    private Long patientTypeId;

    private Long clinicId;

    private Long clinicLocationId;

    private LocalDateTime dischargedOn;

    private List<IpAdmissionBedTransferDetailsDTO> ipAdmissionBedTransferDetailsDTOs;

    //Getters and setters

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Long getiPAdmissionId() {
        return iPAdmissionId;
    }

    public void setiPAdmissionId(Long iPAdmissionId) {
        this.iPAdmissionId = iPAdmissionId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public Long getVisitTypeId() {
        return visitTypeId;
    }

    public void setVisitTypeId(Long visitTypeId) {
        this.visitTypeId = visitTypeId;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public Long getPatientTypeId() {
        return patientTypeId;
    }

    public void setPatientTypeId(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public LocalDateTime getDischargedOn() {
        return dischargedOn;
    }

    public void setDischargedOn(LocalDateTime dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public List<IpAdmissionBedTransferDetailsDTO> getIpAdmissionBedTransferDetailsDTOs() {
        return ipAdmissionBedTransferDetailsDTOs;
    }

    public void setIpAdmissionBedTransferDetailsDTOs(List<IpAdmissionBedTransferDetailsDTO> ipAdmissionBedTransferDetailsDTOs) {
        this.ipAdmissionBedTransferDetailsDTOs = ipAdmissionBedTransferDetailsDTOs;
    }
}
