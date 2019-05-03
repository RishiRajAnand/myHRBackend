package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 01-10-2018
* */

import java.util.List;

public class UpdateCaseOrderDTO {

    private Long orderId;
    private String orderNumber;
    private List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs;
    private List<SaveCaseMedicineDTO> saveCaseMedicineDTOs;
    private List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<SaveInvestigationDetailDTO> getSaveInvestigationDetailDTOs() {
        return saveInvestigationDetailDTOs;
    }

    public void setSaveInvestigationDetailDTOs(List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs) {
        this.saveInvestigationDetailDTOs = saveInvestigationDetailDTOs;
    }

    public List<SaveCaseMedicineDTO> getSaveCaseMedicineDTOs() {
        return saveCaseMedicineDTOs;
    }

    public void setSaveCaseMedicineDTOs(List<SaveCaseMedicineDTO> saveCaseMedicineDTOs) {
        this.saveCaseMedicineDTOs = saveCaseMedicineDTOs;
    }

    public List<SaveTherapyPlanningDTO> getSaveTherapyPlanningDTOs() {
        return saveTherapyPlanningDTOs;
    }

    public void setSaveTherapyPlanningDTOs(List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs) {
        this.saveTherapyPlanningDTOs = saveTherapyPlanningDTOs;
    }
}
