package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;

/**
 * Created by Latha on 20/08/18.
 */

public class SaveComplaintsDTO {

    private Long cmMasterId;
    private Long clinicId;
    private Long userId;
    private Long userDepartmentId;
    private Long clinicLocationId;
    private Long patientId;
    private Long scienceOfMedicineId;
    private boolean isIpCase;
    private String ipDcAdmissionNumber;
    private String chiefComplaint;
    private LocalDateTime caseCreatedDate;
    private LocalDateTime caseCompletedDate;
    private String caseStatus;
    private String erxCaseNumber;
    private String clinicCaseNumber;
    private CmMasterComplaintDTO cmMasterComplaintDTO;
    private CmMasterDetailsDTO cmMasterDetailsDTO;

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public CmMasterComplaintDTO getCmMasterComplaintDTO() {
        return cmMasterComplaintDTO;
    }

    public void setCmMasterComplaintDTO(CmMasterComplaintDTO cmMasterComplaintDTO) {
        this.cmMasterComplaintDTO = cmMasterComplaintDTO;
    }

    public CmMasterDetailsDTO getCmMasterDetailsDTO() {
        return cmMasterDetailsDTO;
    }

    public void setCmMasterDetailsDTO(CmMasterDetailsDTO cmMasterDetailsDTO) {
        this.cmMasterDetailsDTO = cmMasterDetailsDTO;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public String getIpDcAdmissionNumber() {
        return ipDcAdmissionNumber;
    }

    public void setIpDcAdmissionNumber(String ipDcAdmissionNumber) {
        this.ipDcAdmissionNumber = ipDcAdmissionNumber;
    }

    public Long getScienceOfMedicineId() {
        return scienceOfMedicineId;
    }

    public void setScienceOfMedicineId(Long scienceOfMedicineId) {
        this.scienceOfMedicineId = scienceOfMedicineId;
    }

    public Long getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }
}
