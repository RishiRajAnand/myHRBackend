package com.erx.microservice.patientmanagement.service.dischargerequest.savedischargerequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

import java.time.LocalDateTime;

public class SaveDischargeRequestServiceRequest implements CommonServiceRequest {

    private Long ipAdmissionRequestId;

    private Long cmMasterId;

    private LocalDateTime ipAdmissionDate;

    private Long userId;

    private String ipRequestNumber;

    //Constructor

    public SaveDischargeRequestServiceRequest(Long cmMasterId, LocalDateTime ipAdmissionDate, Long userId,
                                              Long ipAdmissionRequestId, String ipRequestNumber) {
        this.cmMasterId = cmMasterId;
        this.ipAdmissionDate = ipAdmissionDate;
        this.userId = userId;
        this.ipAdmissionRequestId = ipAdmissionRequestId;
        this.ipRequestNumber = ipRequestNumber;
    }

    public SaveDischargeRequestServiceRequest() {
    }

    //Getters and setters

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public LocalDateTime getIpAdmissionDate() {
        return ipAdmissionDate;
    }

    public void setIpAdmissionDate(LocalDateTime ipAdmissionDate) {
        this.ipAdmissionDate = ipAdmissionDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIpAdmissionRequestId() {
        return ipAdmissionRequestId;
    }

    public void setIpAdmissionRequestId(Long ipAdmissionRequestId) {
        this.ipAdmissionRequestId = ipAdmissionRequestId;
    }

    public String getIpRequestNumber() {
        return ipRequestNumber;
    }

    public void setIpRequestNumber(String ipRequestNumber) {
        this.ipRequestNumber = ipRequestNumber;
    }
}
