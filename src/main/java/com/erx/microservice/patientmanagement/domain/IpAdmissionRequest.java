package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ip_admission_request")
public class IpAdmissionRequest extends BaseModel {

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cm_master_id")
    private CmMaster cmMaster;

    @NotNull
    @Column(name = "ip_request_number")
    private String ipRequestNumber;

    @NotNull
    @Column(name = "ip_admission_date")
    private LocalDateTime ipAdmissionDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ip_admission_id")
    private IpAdmission ipAdmission;

    //Getters and setters

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public String getIpRequestNumber() {
        return ipRequestNumber;
    }

    public void setIpRequestNumber(String ipRequestNumber) {
        this.ipRequestNumber = ipRequestNumber;
    }

    public LocalDateTime getIpAdmissionDate() {
        return ipAdmissionDate;
    }

    public void setIpAdmissionDate(LocalDateTime ipAdmissionDate) {
        this.ipAdmissionDate = ipAdmissionDate;
    }

    public IpAdmission getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmission ipAdmission) {
        this.ipAdmission = ipAdmission;
    }
}
