package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by latha on 20-11-2017.
 * Updated by Brighty on 29-11-17.
 */

public class BedConfigurationDTO {

    private Long id;

    private String bedCode;

    private String bedNumber;

    private Long bedTypeMasterId;

    private String bedTypeName;

    private Long wardMasterId;

    private String wardName;

    private Long roomConfigurationMasterId;

    private String roomNumber;

    private boolean status;

    private String allocatedStatus;

    private String patientMrn;

    private String patientName;

    private String doctorName;

    private String ipAdmissionNumber;

    private String daycareNumber;

    private Long patientId;

    private Long cmMasterId;

    //Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Long getBedTypeMasterId() {
        return bedTypeMasterId;
    }

    public void setBedTypeMasterId(Long bedTypeMasterId) {
        this.bedTypeMasterId = bedTypeMasterId;
    }

    public Long getWardMasterId() {
        return wardMasterId;
    }

    public void setWardMasterId(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public Long getRoomConfigurationMasterId() {
        return roomConfigurationMasterId;
    }

    public void setRoomConfigurationMasterId(Long roomConfigurationMasterId) {
        this.roomConfigurationMasterId = roomConfigurationMasterId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
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

    public String getAllocatedStatus() {
        return allocatedStatus;
    }

    public void setAllocatedStatus(String allocatedStatus) {
        this.allocatedStatus = allocatedStatus;
    }

    public String getPatientMrn() {
        return patientMrn;
    }

    public void setPatientMrn(String patientMrn) {
        this.patientMrn = patientMrn;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public String getDaycareNumber() {
        return daycareNumber;
    }

    public void setDaycareNumber(String daycareNumber) {
        this.daycareNumber = daycareNumber;
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
}
