package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 16-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cm_case_transfer_history")
public class CmCaseTransferHistory extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_master_id", nullable=false)
    @JsonIgnore
    private CmMaster cmMaster;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "previously_case_treated_by", nullable=false)
    @JsonIgnore
    private UserDetail previouslyCaseTreatedBy;

    @Column(name = "transferred_on", nullable = false)
    private LocalDateTime caseTransferredOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transferred_by", nullable=false)
    @JsonIgnore
    private UserDetail caseTransferredBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transferred_to", nullable=false)
    @JsonIgnore
    private UserDetail caseTransferredTo;

    @Column(name="referral_type_lookup_val_id", nullable=true)
    private long referralTypeLookupValId;

    @Column(name="service_id", nullable=true)
    private long serviceId;

    @Column(name="remarks", nullable=true)
    private String remarks;

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public UserDetail getPreviouslyCaseTreatedBy() {
        return previouslyCaseTreatedBy;
    }

    public void setPreviouslyCaseTreatedBy(UserDetail previouslyCaseTreatedBy) {
        this.previouslyCaseTreatedBy = previouslyCaseTreatedBy;
    }

    public LocalDateTime getCaseTransferredOn() {
        return caseTransferredOn;
    }

    public void setCaseTransferredOn(LocalDateTime caseTransferredOn) {
        this.caseTransferredOn = caseTransferredOn;
    }

    public UserDetail getCaseTransferredBy() {
        return caseTransferredBy;
    }

    public void setCaseTransferredBy(UserDetail caseTransferredBy) {
        this.caseTransferredBy = caseTransferredBy;
    }

    public UserDetail getCaseTransferredTo() {
        return caseTransferredTo;
    }

    public void setCaseTransferredTo(UserDetail caseTransferredTo) {
        this.caseTransferredTo = caseTransferredTo;
    }

    public long getReferralTypeLookupValId() {
        return referralTypeLookupValId;
    }

    public void setReferralTypeLookupValId(long referralTypeLookupValId) {
        this.referralTypeLookupValId = referralTypeLookupValId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
