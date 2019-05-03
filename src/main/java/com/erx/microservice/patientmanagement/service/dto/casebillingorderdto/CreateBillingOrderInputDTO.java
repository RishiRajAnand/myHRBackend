package com.erx.microservice.patientmanagement.service.dto.casebillingorderdto;

/*
* created by raushan on 29-05-2018
* updated by latha on 21-09-2018
* */

import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;

import java.util.List;

public class CreateBillingOrderInputDTO {

    private Long id;
    private Long clinicId;
    private Long clinicLocationId;
    private Long departmentId;
    private Long orderBy;
    private Long lookUpValueIdOfPriority;
    private Long patientId;
    private Long patientAppointmentId;
    private Long ipAdmissionId;
    private String ipDcAdmissionNumber;
    private Long visitTypeMasterId;
    private String type;
    private List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList;

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIpDcAdmissionNumber() {
        return ipDcAdmissionNumber;
    }

    public void setIpDcAdmissionNumber(String ipDcAdmissionNumber) {
        this.ipDcAdmissionNumber = ipDcAdmissionNumber;
    }

    public List<CreateBillingOrderServiceDTO> getCreateBillingOrderServiceDTOList() {
        return createBillingOrderServiceDTOList;
    }

    public void setCreateBillingOrderServiceDTOList(List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList) {
        this.createBillingOrderServiceDTOList = createBillingOrderServiceDTOList;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }
}
