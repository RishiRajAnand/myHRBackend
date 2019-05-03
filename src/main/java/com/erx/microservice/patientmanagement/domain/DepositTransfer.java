package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by brighty on 13-12-17.
 */

@Entity
@Table(name = "patient_deposit_transfer")
public class DepositTransfer extends BaseModel {

    @Column(name = "transfer_code")
    private String transferCode;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "transfer_by", nullable = false)
    private UserStaff userStaff;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "from_account", nullable = false)
    private VisitTypeMaster fromAccount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "to_account", nullable = false)
    private VisitTypeMaster ToAccount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @NotNull
    @Column(name = "transfer_amount")
    private double transferAmount;

    //getters and setters

    public UserStaff getUserStaff() {
        return userStaff;
    }

    public void setUserStaff(UserStaff userStaff) {
        this.userStaff = userStaff;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    public VisitTypeMaster getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(VisitTypeMaster fromAccount) {
        this.fromAccount = fromAccount;
    }

    public VisitTypeMaster getToAccount() {
        return ToAccount;
    }

    public void setToAccount(VisitTypeMaster toAccount) {
        ToAccount = toAccount;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
