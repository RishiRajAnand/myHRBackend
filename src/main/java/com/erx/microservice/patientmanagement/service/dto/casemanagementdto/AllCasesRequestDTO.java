package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 16/08/18.
 */

public class AllCasesRequestDTO {

    private Long clinicId;
    private Long clinicLocationId;
    private Long patientId;
    private Long userId;
   // private Long internalReferralTypeLookupValId;

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

   /* public Long getInternalReferralTypeLookupValId() {
        return internalReferralTypeLookupValId;
    }

    public void setInternalReferralTypeLookupValId(Long internalReferralTypeLookupValId) {
        this.internalReferralTypeLookupValId = internalReferralTypeLookupValId;
    }*/
}
