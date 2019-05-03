package com.erx.microservice.patientmanagement.domain;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
 * created by Bahubali on 17-10-2018
 * */
@Entity
@Table(name = "patient_all_details")
@Where(clause = "record_status=1")
public class PatientAllDetails extends BaseModel {

    @Column(name = "patient_mrn")
    private String patientMRN;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "patient_gender", nullable = false)
    private String patientGender;

    @Column(name = "patient_mobile_number", nullable = false)
    private String patientMobileNumber;

    @Column(name = "patient_age", nullable = false)
    private int patientAge;

    @Column(name = "patient_email", nullable = false)
    private String patientEmail;

    @Column(name = "patient_contact_address")
    private String patientContactAddress;

    @Column(name = "patient_salutation")
    private String patientSalutation;

    @Column(name = "patient_registration_date")
    private LocalDateTime patientRegisteredDate;

    @Column(name = "patient_type_name", nullable = false)
    private String patientTypeName;// general,student,BPL etc

    @Column(name = "patient_type_id", nullable = false)
    private Long patientTypeId;

    @Column(name = "patient_clinic_id")
    private Long patientClinicId;

    @Column(name = "patient_clinic_location_id")
    private Long patientClinicLocationId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "bed_type_name")
    private String bedTypeName;

    @Column(name = "bed_type_id")
    private Long bedTypeId;

    @Column(name = "bed_number")
    private String bedNumber;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "patient_visit_type_name")
    private String patientVisitTypeName;

    @Column(name = "patient_visit_type_master_id")
    private Long patientVisitTypeMasterId;

    @Column(name = "is_day_care_patient")
    private boolean isDayCarePatient;

    @Column(name = "is_patient_registered")
    private boolean isPatientRegistered;

    @Column(name = "ip_admission_number")
    private String ipAdmissionNumber;

    @Column(name = "ip_admission_id")
    private Long ipAdmissionId;

    @Column(name = "ip_admission_date")
    private LocalDateTime ipAdmissionOn;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "user_department_name")
    private String userDepartmentName;

    @Column(name = "user_department_id")
    private Long userDepartmentId;

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

    public LocalDateTime getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(LocalDateTime patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
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

    public LocalDateTime getIpAdmissionOn() {
        return ipAdmissionOn;
    }

    public void setIpAdmissionOn(LocalDateTime ipAdmissionOn) {
        this.ipAdmissionOn = ipAdmissionOn;
    }
}
