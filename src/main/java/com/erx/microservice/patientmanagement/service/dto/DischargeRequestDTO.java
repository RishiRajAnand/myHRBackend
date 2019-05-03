package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by latha on 06-12-2018.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DischargeRequestDTO {

    private Long dischargeRequestId;
    private Long ipAdmissionId;
    private Long patientId;
    private Long cmMasterId;
    private Long actualBedId;
    private String patientMRN;
    private String patientName;
    private String gender;
    private LocalDateTime dateOfAdmission;
    private LocalDate dischargeRequestDate;
    private LocalTime dischargeRequestTime;
    private String dischargeRequestNumber;
    private String ipAdmissionNumber;
    private String visitType;
    private String bedType;
    private String wardName;
    private String roomNumber;
    private String bedNumber;
    private String mobileNumber;
    private String ipAdmissionStatus;

    //getters and setters

    public Long getDischargeRequestId() {
        return dischargeRequestId;
    }

    public void setDischargeRequestId(Long dischargeRequestId) {
        this.dischargeRequestId = dischargeRequestId;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public Long getActualBedId() {
        return actualBedId;
    }

    public void setActualBedId(Long actualBedId) {
        this.actualBedId = actualBedId;
    }

    public String getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(String patientMRN) {
        this.patientMRN = patientMRN;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public LocalDate getDischargeRequestDate() {
        return dischargeRequestDate;
    }

    public void setDischargeRequestDate(LocalDate dischargeRequestDate) {
        this.dischargeRequestDate = dischargeRequestDate;
    }

    public LocalTime getDischargeRequestTime() {
        return dischargeRequestTime;
    }

    public void setDischargeRequestTime(LocalTime dischargeRequestTime) {
        this.dischargeRequestTime = dischargeRequestTime;
    }

    public String getDischargeRequestNumber() {
        return dischargeRequestNumber;
    }

    public void setDischargeRequestNumber(String dischargeRequestNumber) {
        this.dischargeRequestNumber = dischargeRequestNumber;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIpAdmissionStatus() {
        return ipAdmissionStatus;
    }

    public void setIpAdmissionStatus(String ipAdmissionStatus) {
        this.ipAdmissionStatus = ipAdmissionStatus;
    }
}
