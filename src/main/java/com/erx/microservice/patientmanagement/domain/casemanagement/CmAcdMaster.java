package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "acd_master")
public class CmAcdMaster extends BaseModel{

    @Column(name="acd_code")
    private String acdCode;

    @Column(name="description")
    private String acdDescription;

    public String getAcdCode() {
        return acdCode;
    }

    public void setAcdCode(String acdCode) {
        this.acdCode = acdCode;
    }

    public String getAcdDescription() {
        return acdDescription;
    }

    public void setAcdDescription(String acdDescription) {
        this.acdDescription = acdDescription;
    }
}
