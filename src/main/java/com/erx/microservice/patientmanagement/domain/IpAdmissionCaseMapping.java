package com.erx.microservice.patientmanagement.domain;
/*
* created by Latha on 28-11-2017
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;

import javax.persistence.*;

@Entity
@Table(name = "ip_admission_case_mapping")
public class IpAdmissionCaseMapping extends BaseModel {

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ip_admission_id")
    private IpAdmission ipAdmission;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cm_master_id")
    private CmMaster cmMaster;

    public IpAdmission getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmission ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }
}
