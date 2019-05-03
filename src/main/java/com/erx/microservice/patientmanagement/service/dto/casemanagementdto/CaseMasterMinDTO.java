package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;

public class CaseMasterMinDTO {
    private LocalDateTime startDate;

    private String reasonForTherapy;

    private Long userDetailId;

    private Long userId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getReasonForTherapy() {
        return reasonForTherapy;
    }

    public void setReasonForTherapy(String reasonForTherapy) {
        this.reasonForTherapy = reasonForTherapy;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
