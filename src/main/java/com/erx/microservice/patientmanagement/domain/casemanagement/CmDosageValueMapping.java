package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "cm_dosage_value_mapping")
public class CmDosageValueMapping extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_dosage_value_id", nullable=true)
    private CmDosageValue cmDosageValue;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_dosage_master_id", nullable=true)
    private CmDosageMaster cmDosageMaster;

    @Column(name="dosage_number", nullable=true)
    private int dosageNumber;

    public CmDosageValue getCmDosageValue() {
        return cmDosageValue;
    }

    public void setCmDosageValue(CmDosageValue cmDosageValue) {
        this.cmDosageValue = cmDosageValue;
    }

    public CmDosageMaster getCmDosageMaster() {
        return cmDosageMaster;
    }

    public void setCmDosageMaster(CmDosageMaster cmDosageMaster) {
        this.cmDosageMaster = cmDosageMaster;
    }

    public int getDosageNumber() {
        return dosageNumber;
    }

    public void setDosageNumber(int dosageNumber) {
        this.dosageNumber = dosageNumber;
    }
}
