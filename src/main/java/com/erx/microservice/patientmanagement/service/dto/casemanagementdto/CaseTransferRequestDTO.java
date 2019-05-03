package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 16/10/18.
 */

public class CaseTransferRequestDTO {

    private Long clinicId;
    private Long userId;
    private Long caseId;
    private Long transferredDoctorId;
    private Long referralTypeLookupValId;
    private Long serviceId;

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

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getTransferredDoctorId() {
        return transferredDoctorId;
    }

    public void setTransferredDoctorId(Long transferredDoctorId) {
        this.transferredDoctorId = transferredDoctorId;
    }

    public Long getReferralTypeLookupValId() {
        return referralTypeLookupValId;
    }

    public void setReferralTypeLookupValId(Long referralTypeLookupValId) {
        this.referralTypeLookupValId = referralTypeLookupValId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
