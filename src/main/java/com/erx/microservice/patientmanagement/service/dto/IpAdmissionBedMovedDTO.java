package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDateTime;

public class IpAdmissionBedMovedDTO {
    private Long ipBedMovementID;
    private LocalDateTime dateOfAdmission;
    private String patientName;
    private String patientMRN;
    private Long ipAdmissionID;
    private String ipAdmissionNumber;
    private String gender;
    private LocalDateTime transferDate;
    private Long actualBedID;
    private String actualBed;
    private Long fromBedID;
    private String fromBed;
    private String fromWard;
    private Long toBedID;
    private String toBed;
    private String toWard;
    private String transferredBy;
    private String userDepartment;
    private String movedDepartment;
    private boolean isOriginalBed;

    public Long getIpBedMovementID() {
        return ipBedMovementID;
    }

    public void setIpBedMovementID(Long ipBedMovementID) {
        this.ipBedMovementID = ipBedMovementID;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(String patientMRN) {
        this.patientMRN = patientMRN;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public Long getActualBedID() {
        return actualBedID;
    }

    public void setActualBedID(Long actualBedID) {
        this.actualBedID = actualBedID;
    }

    public String getActualBed() {
        return actualBed;
    }

    public void setActualBed(String actualBed) {
        this.actualBed = actualBed;
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

    public String getFromWard() {
        return fromWard;
    }

    public void setFromWard(String fromWard) {
        this.fromWard = fromWard;
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

    public String getToWard() {
        return toWard;
    }

    public void setToWard(String toWard) {
        this.toWard = toWard;
    }

    public String getTransferredBy() {
        return transferredBy;
    }

    public void setTransferredBy(String transferredBy) {
        this.transferredBy = transferredBy;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getMovedDepartment() {
        return movedDepartment;
    }

    public void setMovedDepartment(String movedDepartment) {
        this.movedDepartment = movedDepartment;
    }

    public boolean isOriginalBed() {
        return isOriginalBed;
    }

    public void setOriginalBed(boolean originalBed) {
        isOriginalBed = originalBed;
    }
}
