package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDate;

/**
 * Created by Latha on 16/08/18.
 */
public class PatientVitalsDTO {

    private Long patientId;
    private Long patientVitalId;
    private String patientMRN;
    private String patientSalutation;
    private String patientName;
    private LocalDate patientDateOfBirth;
    private String gender;
    private String mobileNumber;
    private Long bloodGroupId;
    private String bloodGroupName;
    private Long maritalStatusId;
    private String maritalStatusName;
    private Long occupationId;
    private String occupationName;
    private String weight;
    private String height;
    private String bmi;
    private String bp;
    private String headCircumference;
    private String temperature;
    private String allergic;
    private String pulse;
    private String ipDcAdmissionNo;
    private Long visitTypeMasterId;
    private String visitType;
    private String consultantDoctorName;
    private String doctorRegistrationNo;
    private Long ipAdmissionId;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientMRN() {
        return patientMRN;
    }

    public void setPatientMRN(String patientMRN) {
        this.patientMRN = patientMRN;
    }

    public String getPatientSalutation() {
        return patientSalutation;
    }

    public void setPatientSalutation(String patientSalutation) {
        this.patientSalutation = patientSalutation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Long bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public String getBloodGroupName() {
        return bloodGroupName;
    }

    public void setBloodGroupName(String bloodGroupName) {
        this.bloodGroupName = bloodGroupName;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public Long getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Long occupationId) {
        this.occupationId = occupationId;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(String headCircumference) {
        this.headCircumference = headCircumference;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAllergic() {
        return allergic;
    }

    public void setAllergic(String allergic) {
        this.allergic = allergic;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getIpDcAdmissionNo() {
        return ipDcAdmissionNo;
    }

    public void setIpDcAdmissionNo(String ipDcAdmissionNo) {
        this.ipDcAdmissionNo = ipDcAdmissionNo;
    }

    public Long getPatientVitalId() {
        return patientVitalId;
    }

    public void setPatientVitalId(Long patientVitalId) {
        this.patientVitalId = patientVitalId;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getConsultantDoctorName() {
        return consultantDoctorName;
    }

    public void setConsultantDoctorName(String consultantDoctorName) {
        this.consultantDoctorName = consultantDoctorName;
    }

    public String getDoctorRegistrationNo() {
        return doctorRegistrationNo;
    }

    public void setDoctorRegistrationNo(String doctorRegistrationNo) {
        this.doctorRegistrationNo = doctorRegistrationNo;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }
}
