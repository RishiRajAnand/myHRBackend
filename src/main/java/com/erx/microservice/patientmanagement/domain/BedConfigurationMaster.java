package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bed_master")
public class BedConfigurationMaster extends BaseModel {

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    @NotNull
    @Column(name="bed_code")
    private String bedCode;

    @NotNull
    @Column(name = "bed_number")
    private String bedNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bed_type_master_id", nullable = false)
    private BedTypeMaster bedTypeMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ward_master_id", nullable = false)
    private WardMaster wardMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "room_configuration_id", nullable = false)
    private RoomConfigurationMaster roomConfigurationMaster;

    @NotNull
    @Column(name = "status")
    private boolean status;

    @NotNull
    @Column(name = "allocated_status")
    private String allocatedStatus;

    public BedConfigurationMaster() {
        this.status = true;
        this.allocatedStatus="availability";
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public BedTypeMaster getBedTypeMaster() {
        return bedTypeMaster;
    }

    public void setBedTypeMaster(BedTypeMaster bedTypeMaster) {
        this.bedTypeMaster = bedTypeMaster;
    }

    public WardMaster getWardMaster() {
        return wardMaster;
    }

    public void setWardMaster(WardMaster wardMaster) {
        this.wardMaster = wardMaster;
    }

    public RoomConfigurationMaster getRoomConfigurationMaster() {
        return roomConfigurationMaster;
    }

    public void setRoomConfigurationMaster(RoomConfigurationMaster roomConfigurationMaster) {
        this.roomConfigurationMaster = roomConfigurationMaster;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getAllocatedStatus() {
        return allocatedStatus;
    }

    public void setAllocatedStatus(String allocatedStatus) {
        this.allocatedStatus = allocatedStatus;
    }
}
