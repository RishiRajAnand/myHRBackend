package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Latha on 29-11-2017
* */

import java.time.LocalDateTime;

public class IpAdmissionDTO {

    private Long id;
    private Long clinicID;
    private Long clinicLocationId;
    private Long userId;
    private Long patientID;
    private Long userDetailID;
    private Long bedMasterID;
    private Long caseMasterID;
    private String ipAdmissionNumber;
    private LocalDateTime admissionOn;
    private String ipAdmissionNotes;
    private LocalDateTime dischargedOn;
    private String ipAdmissionStatus;
    private boolean dayCare;
    private boolean hasCase;
    private Long iPAdmissionRequestId;
    private Long reAdmitIpAdmissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClinicID() {
        return clinicID;
    }

    public void setClinicID(Long clinicID) {
        this.clinicID = clinicID;
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public Long getUserDetailID() {
        return userDetailID;
    }

    public void setUserDetailID(Long userDetailID) {
        this.userDetailID = userDetailID;
    }

    public Long getBedMasterID() {
        return bedMasterID;
    }

    public void setBedMasterID(Long bedMasterID) {
        this.bedMasterID = bedMasterID;
    }

    public Long getCaseMasterID() {
        return caseMasterID;
    }

    public void setCaseMasterID(Long caseMasterID) {
        this.caseMasterID = caseMasterID;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public LocalDateTime getAdmissionOn() {
        return admissionOn;
    }

    public void setAdmissionOn(LocalDateTime admissionOn) {
        this.admissionOn = admissionOn;
    }

    public String getIpAdmissionNotes() {
        return ipAdmissionNotes;
    }

    public void setIpAdmissionNotes(String ipAdmissionNotes) {
        this.ipAdmissionNotes = ipAdmissionNotes;
    }

    public LocalDateTime getDischargedOn() {
        return dischargedOn;
    }

    public void setDischargedOn(LocalDateTime dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public String getIpAdmissionStatus() {
        return ipAdmissionStatus;
    }

    public void setIpAdmissionStatus(String ipAdmissionStatus) {
        this.ipAdmissionStatus = ipAdmissionStatus;
    }

    public boolean isDayCare() {
        return dayCare;
    }

    public void setDayCare(boolean dayCare) {
        this.dayCare = dayCare;
    }

    public boolean isHasCase() {
        return hasCase;
    }

    public void setHasCase(boolean hasCase) {
        this.hasCase = hasCase;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIPAdmissionRequestId() {
        return iPAdmissionRequestId;
    }

    public void setIPAdmissionRequestId(Long iPAdmissionRequestId) {
        this.iPAdmissionRequestId = iPAdmissionRequestId;
    }

    public Long getReAdmitIpAdmissionId() {
        return reAdmitIpAdmissionId;
    }

    public void setReAdmitIpAdmissionId(Long reAdmitIpAdmissionId) {
        this.reAdmitIpAdmissionId = reAdmitIpAdmissionId;
    }
}
