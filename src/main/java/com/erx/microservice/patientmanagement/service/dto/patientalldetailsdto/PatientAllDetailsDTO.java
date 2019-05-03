package com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * created by Bahubali on 17-10-2018
 * */
public class PatientAllDetailsDTO {

    private String patientMRN;
    private String patientName;
    private String patientGender;
    private String patientMobileNumber;
    private int patientAge;
    private String patientEmail;
    private String patientContactAddress;
    private String patientSalutation;
    private String patientTypeName;// general,student,BPL etc
    private Long patientTypeId;
    private Long patientClinicId;
    private Long patientClinicLocationId;
    private Long patientId;
    private String bedTypeName;
    private Long bedTypeId;
    private String bedNumber;
    private String wardName;
    private String roomNumber;
    private String patientVisitTypeName;
    private Long patientVisitTypeMasterId;
    private boolean isDayCarePatient;
    private boolean isPatientRegistered;
    private String ipAdmissionNumber;
    private Long ipAdmissionId;
    private LocalDateTime ipAdmissionOn;
    private Long doctorId;
    private String doctorName;
    private String userDepartmentName;
    private Long userDepartmentId;
    private LocalDateTime patientRegisteredDate;
    private String context;
    private LocalDate dateOfBirth;

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

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientMobileNumber() {
        return patientMobileNumber;
    }

    public void setPatientMobileNumber(String patientMobileNumber) {
        this.patientMobileNumber = patientMobileNumber;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientContactAddress() {
        return patientContactAddress;
    }

    public void setPatientContactAddress(String patientContactAddress) {
        this.patientContactAddress = patientContactAddress;
    }

    public String getPatientSalutation() {
        return patientSalutation;
    }

    public void setPatientSalutation(String patientSalutation) {
        this.patientSalutation = patientSalutation;
    }

    public String getPatientTypeName() {
        return patientTypeName;
    }

    public void setPatientTypeName(String patientTypeName) {
        this.patientTypeName = patientTypeName;
    }

    public Long getPatientTypeId() {
        return patientTypeId;
    }

    public void setPatientTypeId(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    public Long getPatientClinicId() {
        return patientClinicId;
    }

    public void setPatientClinicId(Long patientClinicId) {
        this.patientClinicId = patientClinicId;
    }

    public Long getPatientClinicLocationId() {
        return patientClinicLocationId;
    }

    public void setPatientClinicLocationId(Long patientClinicLocationId) {
        this.patientClinicLocationId = patientClinicLocationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
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

    public String getPatientVisitTypeName() {
        return patientVisitTypeName;
    }

    public void setPatientVisitTypeName(String patientVisitTypeName) {
        this.patientVisitTypeName = patientVisitTypeName;
    }

    public Long getPatientVisitTypeMasterId() {
        return patientVisitTypeMasterId;
    }

    public void setPatientVisitTypeMasterId(Long patientVisitTypeMasterId) {
        this.patientVisitTypeMasterId = patientVisitTypeMasterId;
    }

    public boolean isDayCarePatient() {
        return isDayCarePatient;
    }

    public void setDayCarePatient(boolean dayCarePatient) {
        isDayCarePatient = dayCarePatient;
    }

    public boolean isPatientRegistered() {
        return isPatientRegistered;
    }

    public void setPatientRegistered(boolean patientRegistered) {
        isPatientRegistered = patientRegistered;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getUserDepartmentName() {
        return userDepartmentName;
    }

    public void setUserDepartmentName(String userDepartmentName) {
        this.userDepartmentName = userDepartmentName;
    }

    public Long getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getIpAdmissionOn() {
        return ipAdmissionOn;
    }

    public void setIpAdmissionOn(LocalDateTime ipAdmissionOn) {
        this.ipAdmissionOn = ipAdmissionOn;
    }

    public LocalDateTime getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(LocalDateTime patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
