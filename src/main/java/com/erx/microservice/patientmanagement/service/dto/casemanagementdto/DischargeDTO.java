package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 05-10-2018
* */

import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentDetailsDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DischargeDTO {

    private long patientID;
    private String patientName;
    private String gender;
    private String mobileNumber;
    private String mrdNumber;
    private LocalDate patientDateOfBirth;
    private String consultantDoctorName;
    private LocalDateTime dateOfAdmission;
    private LocalDateTime dateOfDischarge;
    private LocalDate dischargeDate;
    private LocalTime dischargeTime;
    private Long visitTypeMasterId;
    private String visitType;

    private long caseId;
    private String caseNumber;
    private String chiefComplaint;

    private String adviceOnDischargeSummary;
    private Long ipAdmissionId;
    private String ipAdmissionNumber;
    private String conditionOnAdmissionDoctorSummary;
    private String conditionOnDischargeDoctorSummary;

    private List<CmMasterDetailsDTO> cmMasterDetailsDTOs;
    private List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnAdmission;
    private List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnDischarge;
    private List<CmMedicineTreatmentResponseDTO> internalMedicineList;
    private List<CmMedicineTreatmentResponseDTO> dischargeInternalMedicineList;
    private CmInvestigationDetailsGetDTO conditionOnAdmissionInvestigationMasterBean;
    private CmInvestigationDetailsGetDTO conditionOnDischargeInvestigationMasterBean;
    private DischargeGeneralExaminationBean conditionOnDischargeGeneralExaminationBean;
    private DischargeGeneralExaminationBean conditionOnAdmissionGeneralExaminationBean;

    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMrdNumber() {
        return mrdNumber;
    }

    public void setMrdNumber(String mrdNumber) {
        this.mrdNumber = mrdNumber;
    }

    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getConsultantDoctorName() {
        return consultantDoctorName;
    }

    public void setConsultantDoctorName(String consultantDoctorName) {
        this.consultantDoctorName = consultantDoctorName;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public LocalDateTime getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDateTime dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getAdviceOnDischargeSummary() {
        return adviceOnDischargeSummary;
    }

    public void setAdviceOnDischargeSummary(String adviceOnDischargeSummary) {
        this.adviceOnDischargeSummary = adviceOnDischargeSummary;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public String getConditionOnAdmissionDoctorSummary() {
        return conditionOnAdmissionDoctorSummary;
    }

    public void setConditionOnAdmissionDoctorSummary(String conditionOnAdmissionDoctorSummary) {
        this.conditionOnAdmissionDoctorSummary = conditionOnAdmissionDoctorSummary;
    }

    public String getConditionOnDischargeDoctorSummary() {
        return conditionOnDischargeDoctorSummary;
    }

    public void setConditionOnDischargeDoctorSummary(String conditionOnDischargeDoctorSummary) {
        this.conditionOnDischargeDoctorSummary = conditionOnDischargeDoctorSummary;
    }

    public List<CmTherapyTreatmentDetailsDTO> getTreatmentGivenDetailsOnAdmission() {
        return treatmentGivenDetailsOnAdmission;
    }

    public void setTreatmentGivenDetailsOnAdmission(List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnAdmission) {
        this.treatmentGivenDetailsOnAdmission = treatmentGivenDetailsOnAdmission;
    }

    public List<CmTherapyTreatmentDetailsDTO> getTreatmentGivenDetailsOnDischarge() {
        return treatmentGivenDetailsOnDischarge;
    }

    public void setTreatmentGivenDetailsOnDischarge(List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnDischarge) {
        this.treatmentGivenDetailsOnDischarge = treatmentGivenDetailsOnDischarge;
    }

    public List<CmMedicineTreatmentResponseDTO> getInternalMedicineList() {
        return internalMedicineList;
    }

    public void setInternalMedicineList(List<CmMedicineTreatmentResponseDTO> internalMedicineList) {
        this.internalMedicineList = internalMedicineList;
    }

    public List<CmMedicineTreatmentResponseDTO> getDischargeInternalMedicineList() {
        return dischargeInternalMedicineList;
    }

    public void setDischargeInternalMedicineList(List<CmMedicineTreatmentResponseDTO> dischargeInternalMedicineList) {
        this.dischargeInternalMedicineList = dischargeInternalMedicineList;
    }

    public CmInvestigationDetailsGetDTO getConditionOnAdmissionInvestigationMasterBean() {
        return conditionOnAdmissionInvestigationMasterBean;
    }

    public void setConditionOnAdmissionInvestigationMasterBean(CmInvestigationDetailsGetDTO conditionOnAdmissionInvestigationMasterBean) {
        this.conditionOnAdmissionInvestigationMasterBean = conditionOnAdmissionInvestigationMasterBean;
    }

    public CmInvestigationDetailsGetDTO getConditionOnDischargeInvestigationMasterBean() {
        return conditionOnDischargeInvestigationMasterBean;
    }

    public void setConditionOnDischargeInvestigationMasterBean(CmInvestigationDetailsGetDTO conditionOnDischargeInvestigationMasterBean) {
        this.conditionOnDischargeInvestigationMasterBean = conditionOnDischargeInvestigationMasterBean;
    }

    public DischargeGeneralExaminationBean getConditionOnDischargeGeneralExaminationBean() {
        return conditionOnDischargeGeneralExaminationBean;
    }

    public void setConditionOnDischargeGeneralExaminationBean(DischargeGeneralExaminationBean conditionOnDischargeGeneralExaminationBean) {
        this.conditionOnDischargeGeneralExaminationBean = conditionOnDischargeGeneralExaminationBean;
    }

    public DischargeGeneralExaminationBean getConditionOnAdmissionGeneralExaminationBean() {
        return conditionOnAdmissionGeneralExaminationBean;
    }

    public void setConditionOnAdmissionGeneralExaminationBean(DischargeGeneralExaminationBean conditionOnAdmissionGeneralExaminationBean) {
        this.conditionOnAdmissionGeneralExaminationBean = conditionOnAdmissionGeneralExaminationBean;
    }

    public List<CmMasterDetailsDTO> getCmMasterDetailsDTOs() {
        return cmMasterDetailsDTOs;
    }

    public void setCmMasterDetailsDTOs(List<CmMasterDetailsDTO> cmMasterDetailsDTOs) {
        this.cmMasterDetailsDTOs = cmMasterDetailsDTOs;
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

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public LocalTime getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(LocalTime dischargeTime) {
        this.dischargeTime = dischargeTime;
    }
}
