package com.erx.microservice.patientmanagement.service.dto;

/**
 * Updated by Brighty on 12-06-2018.
 */

import java.time.LocalDateTime;
import java.util.List;

public class IpAdmissionTrackerDTO {

    private Long iPAdmissionId;
    private Long patientId;
    private Long actualBedId;
    private String patientMRN;
    private String patientName;
    private String gender;
    private String doctorName;
    private String visitType;
    private LocalDateTime dateOfAdmission;
    private String visitId;
    private String bedType;
    private String wardName;
    private String roomNumber;
    private String bedNumber;
    private Long ipAdmissionBedMovementId;
    private Long bedMovedToId;
    private Long doctorId;
    private Long departmentId;
    private String departmentName;
    private String ipAdmissionNotes;
    private String age;
    private String mobileNumber;
    private String patientType;
    private String ipAdmissionStatus;
    private List<IpDischargeDetailDTO> ipDischargeDetailDTOs;
    private boolean isOriginalBed;

    //Getters and setters

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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
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

    public Long getIpAdmissionBedMovementId() {
        return ipAdmissionBedMovementId;
    }

    public void setIpAdmissionBedMovementId(Long ipAdmissionBedMovementId) {
        this.ipAdmissionBedMovementId = ipAdmissionBedMovementId;
    }

    public Long getBedMovedToId() {
        return bedMovedToId;
    }

    public void setBedMovedToId(Long bedMovedToId) {
        this.bedMovedToId = bedMovedToId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getIpAdmissionNotes() {
        return ipAdmissionNotes;
    }

    public void setIpAdmissionNotes(String ipAdmissionNotes) {
        this.ipAdmissionNotes = ipAdmissionNotes;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getIpAdmissionStatus() {
        return ipAdmissionStatus;
    }

    public void setIpAdmissionStatus(String ipAdmissionStatus) {
        this.ipAdmissionStatus = ipAdmissionStatus;
    }

    public List<IpDischargeDetailDTO> getIpDischargeDetailDTOs() {
        return ipDischargeDetailDTOs;
    }

    public void setIpDischargeDetailDTOs(List<IpDischargeDetailDTO> ipDischargeDetailDTOs) {
        this.ipDischargeDetailDTOs = ipDischargeDetailDTOs;
    }

    public boolean isOriginalBed() {
        return isOriginalBed;
    }

    public void setOriginalBed(boolean originalBed) {
        isOriginalBed = originalBed;
    }
}
