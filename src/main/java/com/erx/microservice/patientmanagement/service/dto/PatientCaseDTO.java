package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Latha on 28-11-2017
* */

import java.time.LocalDateTime;

public class PatientCaseDTO {

    private long patientID;
    private long caseID;
    private LocalDateTime caseCreatedDate;
    private LocalDateTime caseCompletedDate;
    private String caseChiefComplaint;
    private String caseStatus;
    private String caseClinicID;
    private long doctorID;
    private String doctorName;

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    public long getCaseID() {
        return caseID;
    }

    public void setCaseID(long caseID) {
        this.caseID = caseID;
    }

    public LocalDateTime getCaseCreatedDate() {
        return caseCreatedDate;
    }

    public void setCaseCreatedDate(LocalDateTime caseCreatedDate) {
        this.caseCreatedDate = caseCreatedDate;
    }

    public LocalDateTime getCaseCompletedDate() {
        return caseCompletedDate;
    }

    public void setCaseCompletedDate(LocalDateTime caseCompletedDate) {
        this.caseCompletedDate = caseCompletedDate;
    }

    public String getCaseChiefComplaint() {
        return caseChiefComplaint;
    }

    public void setCaseChiefComplaint(String caseChiefComplaint) {
        this.caseChiefComplaint = caseChiefComplaint;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseClinicID() {
        return caseClinicID;
    }

    public void setCaseClinicID(String caseClinicID) {
        this.caseClinicID = caseClinicID;
    }

    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
