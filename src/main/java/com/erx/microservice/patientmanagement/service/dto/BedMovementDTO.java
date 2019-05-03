package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Latha on 29-11-2017
* */

public class BedMovementDTO {

    private Long id;
    private Long ipAdmissionID;
    private Long fromBedMasterID;
    private Long toBedMasterID;
    private Long userID;
    private Long userDetailID;
    private String ipAdmissionNotes;
    private Long departmentID;
    private Long subDepartmentID;
    private boolean isOriginalBed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIpAdmissionID() {
        return ipAdmissionID;
    }

    public void setIpAdmissionID(Long ipAdmissionID) {
        this.ipAdmissionID = ipAdmissionID;
    }

    public Long getFromBedMasterID() {
        return fromBedMasterID;
    }

    public void setFromBedMasterID(Long fromBedMasterID) {
        this.fromBedMasterID = fromBedMasterID;
    }

    public Long getToBedMasterID() {
        return toBedMasterID;
    }

    public void setToBedMasterID(Long toBedMasterID) {
        this.toBedMasterID = toBedMasterID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserDetailID() {
        return userDetailID;
    }

    public void setUserDetailID(Long userDetailID) {
        this.userDetailID = userDetailID;
    }

    public String getIpAdmissionNotes() {
        return ipAdmissionNotes;
    }

    public void setIpAdmissionNotes(String ipAdmissionNotes) {
        this.ipAdmissionNotes = ipAdmissionNotes;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public Long getSubDepartmentID() {
        return subDepartmentID;
    }

    public void setSubDepartmentID(Long subDepartmentID) {
        this.subDepartmentID = subDepartmentID;
    }

    public boolean isOriginalBed() {
        return isOriginalBed;
    }

    public void setOriginalBed(boolean originalBed) {
        isOriginalBed = originalBed;
    }
}
