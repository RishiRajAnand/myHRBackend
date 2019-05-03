package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;

@Entity
@Table(name = "ip_bed_movement_detail")

public class IpAdmissionBedMovement extends BaseModel {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ip_admission_id")
    private IpAdmission ipAdmission;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_moved_from")
    private BedConfigurationMaster bedMovedFrom;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_moved_to")
    private BedConfigurationMaster bedMovedTo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_moved_by", nullable = true)
    private UserStaff userStaff;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "bed_moved_department", nullable = true)
    private DepartmentMaster bedMovedDepartment;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "bed_moved_subdepartment", nullable = true)
    private DepartmentMaster bedMovedSubDepartment;

    @Column(name = "is_current_bed")
    private boolean currentBed;

    public IpAdmission getIpAdmission() {
        return ipAdmission;
    }

    public void setIpAdmission(IpAdmission ipAdmission) {
        this.ipAdmission = ipAdmission;
    }

    public BedConfigurationMaster getBedMovedFrom() {
        return bedMovedFrom;
    }

    public void setBedMovedFrom(BedConfigurationMaster bedMovedFrom) {
        this.bedMovedFrom = bedMovedFrom;
    }

    public BedConfigurationMaster getBedMovedTo() {
        return bedMovedTo;
    }

    public void setBedMovedTo(BedConfigurationMaster bedMovedTo) {
        this.bedMovedTo = bedMovedTo;
    }

    public UserStaff getUserStaff() {
        return userStaff;
    }

    public void setUserStaff(UserStaff userStaff) {
        this.userStaff = userStaff;
    }

    public DepartmentMaster getBedMovedDepartment() {
        return bedMovedDepartment;
    }

    public void setBedMovedDepartment(DepartmentMaster bedMovedDepartment) {
        this.bedMovedDepartment = bedMovedDepartment;
    }

    public DepartmentMaster getBedMovedSubDepartment() {
        return bedMovedSubDepartment;
    }

    public void setBedMovedSubDepartment(DepartmentMaster bedMovedSubDepartment) {
        this.bedMovedSubDepartment = bedMovedSubDepartment;
    }

    public boolean isCurrentBed() {
        return currentBed;
    }

    public void setCurrentBed(boolean currentBed) {
        this.currentBed = currentBed;
    }
}
