package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDate;

/**
 * Created by Latha on 16/08/18.
 */
public class PatientCaseCountSearchDTO {

    private Long patientId;
    private String patientMRN;
    private String patientSalutation;
    private String patientName;
    private LocalDate patientDateOfBirth;
    private String gender;
    private String mobileNumber;
    private Long patientCaseCount;


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

    public Long getPatientCaseCount() {
        return patientCaseCount;
    }

    public void setPatientCaseCount(Long patientCaseCount) {
        this.patientCaseCount = patientCaseCount;
    }
}
