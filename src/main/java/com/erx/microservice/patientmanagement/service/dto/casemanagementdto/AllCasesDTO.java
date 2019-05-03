package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;

/**
 * Created by Latha on 16/08/18.
 */

public class AllCasesDTO {

    private Long patientId;
    private Long patientCaseCount;
    private Long caseId;
    private String caseNumber;
    private String chiefComplaint;
    private LocalDateTime caseCreatedDate;
    private String caseStatus;
    private String ipDcAdmissionNumber;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientCaseCount() {
        return patientCaseCount;
    }

    public void setPatientCaseCount(Long patientCaseCount) {
        this.patientCaseCount = patientCaseCount;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
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

    public LocalDateTime getCaseCreatedDate() {
        return caseCreatedDate;
    }

    public void setCaseCreatedDate(LocalDateTime caseCreatedDate) {
        this.caseCreatedDate = caseCreatedDate;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getIpDcAdmissionNumber() {
        return ipDcAdmissionNumber;
    }

    public void setIpDcAdmissionNumber(String ipDcAdmissionNumber) {
        this.ipDcAdmissionNumber = ipDcAdmissionNumber;
    }
}
