package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by Brighty on 11-06-2018.
 */

public class IpAdmissionRequestDTO {

    private Long IpAdmissionRequestId;

    private Long patientId;

    private String patientName;

    private String patientMrn;

    private String ipRequestNumber;

    private Long ipAdmissionId;

    private String ipAdmissionNumber;

    private String requestCreatedDate;

    private String ipAdmissionDate;

    private String requestedBy;

    private Long cmMasterId;

    //Getters and setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getIpAdmissionRequestId() {
        return IpAdmissionRequestId;
    }

    public void setIpAdmissionRequestId(Long ipAdmissionRequestId) {
        IpAdmissionRequestId = ipAdmissionRequestId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMrn() {
        return patientMrn;
    }

    public void setPatientMrn(String patientMrn) {
        this.patientMrn = patientMrn;
    }

    public String getIpRequestNumber() {
        return ipRequestNumber;
    }

    public void setIpRequestNumber(String ipRequestNumber) {
        this.ipRequestNumber = ipRequestNumber;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public String getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public void setRequestCreatedDate(String requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

    public String getIpAdmissionDate() {
        return ipAdmissionDate;
    }

    public void setIpAdmissionDate(String ipAdmissionDate) {
        this.ipAdmissionDate = ipAdmissionDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }
}
