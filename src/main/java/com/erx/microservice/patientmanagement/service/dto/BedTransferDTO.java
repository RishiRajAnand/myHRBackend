package com.erx.microservice.patientmanagement.service.dto;

/*
 * created by Latha on 29-11-2017
 * */

public class BedTransferDTO {

    private Long id;
    private Long ipAdmissionID;
    private Long bedMasterID;
    private Long userID;
    private Long userDetailID;
    private String ipAdmissionNotes;
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

    public Long getBedMasterID() {
        return bedMasterID;
    }

    public void setBedMasterID(Long bedMasterID) {
        this.bedMasterID = bedMasterID;
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

    public boolean isOriginalBed() {
        return isOriginalBed;
    }

    public void setOriginalBed(boolean originalBed) {
        isOriginalBed = originalBed;
    }
}
