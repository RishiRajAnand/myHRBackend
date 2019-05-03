package com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail;

/*
* created by raushan on 24-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveTherapyMedicineDetailServiceResponse extends CommonServiceResponse {

    private Long cmNextVisitId;
    private Long notificationId;

    //constructor

    public SaveTherapyMedicineDetailServiceResponse() {
    }

    public SaveTherapyMedicineDetailServiceResponse(Long cmNextVisitId, Long notificationId) {
        this.cmNextVisitId = cmNextVisitId;
        this.notificationId = notificationId;
    }

    //getters and setters

    public Long getCmNextVisitId() {
        return cmNextVisitId;
    }

    public void setCmNextVisitId(Long cmNextVisitId) {
        this.cmNextVisitId = cmNextVisitId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
}
