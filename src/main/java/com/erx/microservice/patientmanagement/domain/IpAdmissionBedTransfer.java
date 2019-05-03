package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;

@Entity
@Table(name = "ip_bed_transfer_detail")
public class IpAdmissionBedTransfer extends BaseModel {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ip_admission_id")
    private IpAdmission ipAdmission;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_transferred_from")
    private BedConfigurationMaster bedTransferredFrom;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_transferred_to")
    private BedConfigurationMaster bedTransferredTo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_transferred_by", nullable = true)
    private UserStaff userStaff;

    public IpAdmission getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmission ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

    public BedConfigurationMaster getBedTransferredFrom() {
        return bedTransferredFrom;
    }

    public void setBedTransferredFrom(BedConfigurationMaster bedTransferredFrom) {
        this.bedTransferredFrom = bedTransferredFrom;
    }

    public BedConfigurationMaster getBedTransferredTo() {
        return bedTransferredTo;
    }

    public void setBedTransferredTo(BedConfigurationMaster bedTransferredTo) {
        this.bedTransferredTo = bedTransferredTo;
    }

    public UserStaff getUserStaff() {
        return userStaff;
    }

    public void setUserStaff(UserStaff userStaff) {
        this.userStaff = userStaff;
    }
}
