package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 28-11-2017
* */

import com.erx.microservice.patientmanagement.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cm_master")

public class CmMaster extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clinic_location_id", nullable = false)
    private ClinicLocation clinicLocation;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_detail_id", nullable = true)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // private SystemOfMedicine systemOfMedicine;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "ip_admission_id", nullable = true)
    private IpAdmission ipAdmission;

    //private DiseaseMasterDetails diseaseMasterDetails;
    @Column(name = "is_ip_case")
    private boolean isIpCase;

    @Column(nullable = false, name = "chief_complaint")
    private String chiefComplaint;

    @Column(nullable = false, name = "case_created_date")
    private LocalDateTime caseCreatedDate;

    @Column(nullable = true, name = "case_completed_date")
    private LocalDateTime caseCompletedDate;

    @Column(nullable = false, name = "case_status")
    private String caseStatus;

    @Column(nullable = true, name = "erx_case_number")
    private String erxCaseNumber;

    @Column(nullable = false, name = "clinic_case_number")
    private String clinicCaseNumber;

    @Column(name = "consent_form_submitted_date", nullable = true)
    private LocalDateTime consentFormSubmittedDate;

    //private CmComplaint cmComplaint;
    @OneToMany(mappedBy = "cmMaster",fetch = FetchType.LAZY)
    private List<CmMasterDetails> cmMasterDetailedList;
    //private List<CM_MasterComplaints> cmMasterComplaintsList;
    //private List<TherapyPlanning> therapyPlanning;

    @Column(name = "consent_form_file_path", nullable = true)
    private String consentFormFilePath;

    @Column(name = "advice_on_discharge", nullable = true)
    private String adviceOnDischarge;

    @Column(name = "is_wellness")
    private boolean wellness;

    @Column(nullable = true, name = "allopathy_case_status")
    private String allopathyCaseStatus;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "duty_doctor_id", nullable = true)
    private UserDetail dutyDoctor;

    @Column(nullable = true, name = "science_medicine_id")
    private Long scienceMedicineId;


    @Column(name = "is_internal_transfer", nullable = true)
    private boolean isInternalTransfer;

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public IpAdmission getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmission ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

    public boolean isIpCase() {
        return isIpCase;
    }

    public void setIpCase(boolean ipCase) {
        isIpCase = ipCase;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getErxCaseNumber() {
        return erxCaseNumber;
    }

    public void setErxCaseNumber(String erxCaseNumber) {
        this.erxCaseNumber = erxCaseNumber;
    }

    public String getClinicCaseNumber() {
        return clinicCaseNumber;
    }

    public void setClinicCaseNumber(String clinicCaseNumber) {
        this.clinicCaseNumber = clinicCaseNumber;
    }

    public LocalDateTime getConsentFormSubmittedDate() {
        return consentFormSubmittedDate;
    }

    public void setConsentFormSubmittedDate(LocalDateTime consentFormSubmittedDate) {
        this.consentFormSubmittedDate = consentFormSubmittedDate;
    }

    public String getConsentFormFilePath() {
        return consentFormFilePath;
    }

    public void setConsentFormFilePath(String consentFormFilePath) {
        this.consentFormFilePath = consentFormFilePath;
    }

    public String getAdviceOnDischarge() {
        return adviceOnDischarge;
    }

    public void setAdviceOnDischarge(String adviceOnDischarge) {
        this.adviceOnDischarge = adviceOnDischarge;
    }

    public boolean isWellness() {
        return wellness;
    }

    public void setWellness(boolean wellness) {
        this.wellness = wellness;
    }

    public String getAllopathyCaseStatus() {
        return allopathyCaseStatus;
    }

    public void setAllopathyCaseStatus(String allopathyCaseStatus) {
        this.allopathyCaseStatus = allopathyCaseStatus;
    }

    public UserDetail getDutyDoctor() {
        return dutyDoctor;
    }

    public void setDutyDoctor(UserDetail dutyDoctor) {
        this.dutyDoctor = dutyDoctor;
    }

    public Long getScienceMedicineId() {
        return scienceMedicineId;
    }

    public void setScienceMedicineId(Long scienceMedicineId) {
        this.scienceMedicineId = scienceMedicineId;
    }

    public List<CmMasterDetails> getCmMasterDetailedList() {
        return cmMasterDetailedList;
    }

    public void setCmMasterDetailedList(List<CmMasterDetails> cmMasterDetailedList) {
        this.cmMasterDetailedList = cmMasterDetailedList;
    }

    public boolean isInternalTransfer() {
        return isInternalTransfer;
    }

    public void setInternalTransfer(boolean internalTransfer) {
        isInternalTransfer = internalTransfer;
    }
}
