package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;
import java.util.List;

public class SaveCaseDetailWithoutCaseSheetDTO {
    private Long patientId;
    private Long clinicLocationId;
    private  Long orderId;
    private boolean isIpCase;
    private List<IdQuantityDTO> idQuantityDTOs;

    private List<Long>  medicineTypeIds;

    private LocalDateTime startDate;

    private String reasonForTherapy;

    private String instruction;

    private Long userId;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isIpCase() {
        return isIpCase;
    }

    public void setIpCase(boolean ipCase) {
        isIpCase = ipCase;
    }

    public List<IdQuantityDTO> getIdQuantityDTOs() {
        return idQuantityDTOs;
    }

    public void setIdQuantityDTOs(List<IdQuantityDTO> idQuantityDTOs) {
        this.idQuantityDTOs = idQuantityDTOs;
    }
}
