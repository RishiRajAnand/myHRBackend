package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mkpatil on 28/12/17.
 */
public class PatientSearchDTO {

    private Long ipAdmissionId;
    private String ipAdmissionNumber;
    private Long patientId;
    private Long actualBedId;
    private String patientMRN;
    private String patientName;
    private String gender;
    private String doctorName;
    private Long visitTypeMasterId;
    private String visitType;
    private LocalDateTime dateOfAdmission;
    private String visitId;
    private Long bedTypeId;
    private String bedType;
    private String wardName;
    private String roomNumber;
    private String bedNumber;
    private Long ipAdmissionBedMovementId;
    private Long bedMovedToId;
    private Long doctorId;
    private String doctorSpeciality;
    private Long doctorSpecialityId;
    private String doctorSpecialityDescription;
    private Long departmentId;
    private String departmentName;
    private String ipAdmissionNotes;
    private int age;
    private String mobileNumber;
    private Long patientTypeId;
    private String patientType;
    private LocalDateTime patientRegisteredDate;
    private boolean isDayCare;
    private List<PatientAppointmentDTO> patientAppointmentDTOs;
    private String referredBy;
    private String patientSalutation;
    private String patientCRN;
    private String email;
    private String address;
    private Long patientClinicId;
    private  LocalDateTime dischargedOn;
    public String getDoctorSpecialityDescription() {
        return doctorSpecialityDescription;
    }

    public void setDoctorSpecialityDescription(String doctorSpecialityDescription) {
        this.doctorSpecialityDescription = doctorSpecialityDescription;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public Long getDoctorSpecialityId() {
        return doctorSpecialityId;
    }

    public void setDoctorSpecialityId(Long doctorSpecialityId) {
        this.doctorSpecialityId = doctorSpecialityId;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public List<PatientAppointmentDTO> getPatientAppointmentDTOs() {
        return patientAppointmentDTOs;
    }

    public void setPatientAppointmentDTOs(List<PatientAppointmentDTO> patientAppointmentDTOs) {
        this.patientAppointmentDTOs = patientAppointmentDTOs;
    }

    public Long getPatientTypeId() {
        return patientTypeId;
    }

    public void setPatientTypeId(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public LocalDateTime getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(LocalDateTime patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
    }

    public boolean isDayCare() {
        return isDayCare;
    }

    public void setDayCare(boolean dayCare) {
        isDayCare = dayCare;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getPatientSalutation() {
        return patientSalutation;
    }

    public void setPatientSalutation(String patientSalutation) {
        this.patientSalutation = patientSalutation;
    }

    public String getPatientCRN() {
        return patientCRN;
    }

    public void setPatientCRN(String patientCRN) {
        this.patientCRN = patientCRN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPatientClinicId() {
        return patientClinicId;
    }

    public void setPatientClinicId(Long patientClinicId) {
        this.patientClinicId = patientClinicId;
    }

    public LocalDateTime getDischargedOn() {
        return dischargedOn;
    }
    public void setDischargedOn(LocalDateTime dischargedOn) {
        this.dischargedOn = dischargedOn;
    }
}
