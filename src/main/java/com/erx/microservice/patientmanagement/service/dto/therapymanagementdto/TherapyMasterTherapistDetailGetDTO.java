package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

public class TherapyMasterTherapistDetailGetDTO {

    private Long therapyMasterTherapistDetailId;
    private Long userId;
    private String userName;

    public Long getTherapyMasterTherapistDetailId() {
        return therapyMasterTherapistDetailId;
    }

    public void setTherapyMasterTherapistDetailId(Long therapyMasterTherapistDetailId) {
        this.therapyMasterTherapistDetailId = therapyMasterTherapistDetailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
