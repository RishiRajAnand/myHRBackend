package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
 * created by Latha on 01-10-2018
 * */

import java.time.LocalDateTime;
import java.util.List;

public class BillingOrderDTO {

    private Long id;
    private String orderId;
    private Long orderBy;
    private Long lookUpValueIdOfPriority;
    private Long patientId;
    private Long clinicLocationId;
    private List<BillingOrderServiceDTO> billingOrderServiceDTOs;
    private String visitId;
    private Long departmentId;
    private String departmentName;
    private String orderName;
    private LocalDateTime createdOn;
    private String priority;
    private Long visitTypeMasterId;
    private String visitType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Long orderBy) {
        this.orderBy = orderBy;
    }

    public Long getLookUpValueIdOfPriority() {
        return lookUpValueIdOfPriority;
    }

    public void setLookUpValueIdOfPriority(Long lookUpValueIdOfPriority) {
        this.lookUpValueIdOfPriority = lookUpValueIdOfPriority;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public List<BillingOrderServiceDTO> getBillingOrderServiceDTOs() {
        return billingOrderServiceDTOs;
    }

    public void setBillingOrderServiceDTOs(List<BillingOrderServiceDTO> billingOrderServiceDTOs) {
        this.billingOrderServiceDTOs = billingOrderServiceDTOs;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }
}
