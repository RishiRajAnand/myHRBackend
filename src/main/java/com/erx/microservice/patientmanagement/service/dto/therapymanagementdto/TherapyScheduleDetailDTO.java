package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

import java.util.List;

public class TherapyScheduleDetailDTO {
    private Long id;
    private Long duration;
    private int numTherapist;
    private Long allocatedBedId;
    private String  allocatedBedName;
    private List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs;
    private List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getNumTherapist() {
        return numTherapist;
    }

    public void setNumTherapist(int numTherapist) {
        this.numTherapist = numTherapist;
    }

    public Long getAllocatedBedId() {
        return allocatedBedId;
    }

    public void setAllocatedBedId(Long allocatedBedId) {
        this.allocatedBedId = allocatedBedId;
    }

    public String getAllocatedBedName() {
        return allocatedBedName;
    }

    public void setAllocatedBedName(String allocatedBedName) {
        this.allocatedBedName = allocatedBedName;
    }

    public List<TherapyMasterRoomDetailGetDTO> getTherapyMasterRoomDetailGetDTOs() {
        return therapyMasterRoomDetailGetDTOs;
    }

    public void setTherapyMasterRoomDetailGetDTOs(List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs) {
        this.therapyMasterRoomDetailGetDTOs = therapyMasterRoomDetailGetDTOs;
    }

    public List<TherapyMasterTherapistDetailGetDTO> getTherapyMasterTherapistDetailGetDTOs() {
        return therapyMasterTherapistDetailGetDTOs;
    }

    public void setTherapyMasterTherapistDetailGetDTOs(List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs) {
        this.therapyMasterTherapistDetailGetDTOs = therapyMasterTherapistDetailGetDTOs;
    }
}
