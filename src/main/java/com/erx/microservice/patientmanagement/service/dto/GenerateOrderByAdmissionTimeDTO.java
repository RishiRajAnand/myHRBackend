package com.erx.microservice.patientmanagement.service.dto;

/*
 * created by Brighty on 05-07-2018
 * */

public class GenerateOrderByAdmissionTimeDTO {

    private String admissionOn;

    private Long clinicLocationId;

    private Long patientId;

    private Long ipAdmissionId;

    private Long visitTypeMasterId;

    private Long userId;

    private Long bedTypeId;

    //Constructor

    public GenerateOrderByAdmissionTimeDTO() {
    }

    //Getters and setters

    public String getAdmissionOn() {
        return admissionOn;
    }

    public void setAdmissionOn(String admissionOn) {
        this.admissionOn = admissionOn;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }
}
