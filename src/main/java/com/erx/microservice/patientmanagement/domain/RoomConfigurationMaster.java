package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room_configuration_master")
public class RoomConfigurationMaster extends BaseModel {

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    @NotNull
    @Column(name = "room_code")
    private String roomCode;

    @NotNull
    @Column(name = "room_name")
    private String roomName;

    @NotNull
    @Column(name = "room_number")
    private String roomNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bed_type_master_id", nullable = false)
    private BedTypeMaster bedTypeMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ward_master_id", nullable = false)
    private WardMaster wardMaster;

    @NotNull
    @Column(name = "status")
    private boolean status;

    public RoomConfigurationMaster() {
        this.status = true;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
