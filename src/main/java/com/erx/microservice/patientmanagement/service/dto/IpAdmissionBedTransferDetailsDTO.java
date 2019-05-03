package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 31-05-2018
* */

import java.time.LocalDateTime;

public class IpAdmissionBedTransferDetailsDTO {

    private LocalDateTime dateOfAdmission;

    private Long patientId;

    private Long ipAdmissionID;

    private String ipAdmissionNumber;

    private LocalDateTime transferDate;

    private Long fromBedID;

    private String fromBed;

    private Long toBedID;

    private String toBed;

    private String transferredBy;

    private String userDepartmentId;

    //Getters and setters

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getIpAdmissionID() {
        return ipAdmissionID;
    }

    public void setIpAdmissionID(Long ipAdmissionID) {
        this.ipAdmissionID = ipAdmissionID;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public Long getFromBedID() {
        return fromBedID;
    }

    public void setFromBedID(Long fromBedID) {
        this.fromBedID = fromBedID;
    }

    public String getFromBed() {
        return fromBed;
    }

    public void setFromBed(String fromBed) {
        this.fromBed = fromBed;
    }

    public Long getToBedID() {
        return toBedID;
    }

    public void setToBedID(Long toBedID) {
        this.toBedID = toBedID;
    }

    public String getToBed() {
        return toBed;
    }

    public void setToBed(String toBed) {
        this.toBed = toBed;
    }

    public String getTransferredBy() {
        return transferredBy;
    }

    public void setTransferredBy(String transferredBy) {
        this.transferredBy = transferredBy;
    }

    public String getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(String userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }
}
