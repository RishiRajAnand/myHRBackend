package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 04-09-2018
* */


public class TherapyMasterGetAllDTO {

    private Long therapyMasterId;
    private Long serviceCatalogueId;
    private String therapyName;//service catalogue name
    private Long therapyDepartmentId;
    private String therapyDepartmentName;
    private Long duration;
    private boolean isMedicineCharged;
    private int roomRequired;

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public String getTherapyName() {
        return therapyName;
    }

    public void setTherapyName(String therapyName) {
        this.therapyName = therapyName;
    }

    public Long getTherapyDepartmentId() {
        return therapyDepartmentId;
    }

    public void setTherapyDepartmentId(Long therapyDepartmentId) {
        this.therapyDepartmentId = therapyDepartmentId;
    }

    public String getTherapyDepartmentName() {
        return therapyDepartmentName;
    }

    public void setTherapyDepartmentName(String therapyDepartmentName) {
        this.therapyDepartmentName = therapyDepartmentName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public boolean isMedicineCharged() {
        return isMedicineCharged;
    }

    public void setMedicineCharged(boolean medicineCharged) {
        isMedicineCharged = medicineCharged;
    }

    public int getRoomRequired() {
        return roomRequired;
    }

    public void setRoomRequired(int roomRequired) {
        this.roomRequired = roomRequired;
    }
}
