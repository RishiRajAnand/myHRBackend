package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

import java.util.List;

public class CmTherapyTreatmentDetailsDTO {

    private Long therapyPlanningId;
    private Long bmOrderId;
    private Long serviceCatalogueId;
    private String therapyName;
    private int numberOfDays;
    private String instructions;
    private long periodicInterval; // repeat after
    private List<CmTherapyTreatmentMedicineDTO> cmTherapyTreatmentMedicineDTOs;
    private List<CmTherapyTreatmentMedicineTypeDTO> cmTherapyTreatmentMedicineTypeDTOs;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getTherapyPlanningId() {
        return therapyPlanningId;
    }

    public void setTherapyPlanningId(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
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

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public long getPeriodicInterval() {
        return periodicInterval;
    }

    public void setPeriodicInterval(long periodicInterval) {
        this.periodicInterval = periodicInterval;
    }

    public List<CmTherapyTreatmentMedicineDTO> getCmTherapyTreatmentMedicineDTOs() {
        return cmTherapyTreatmentMedicineDTOs;
    }

    public void setCmTherapyTreatmentMedicineDTOs(List<CmTherapyTreatmentMedicineDTO> cmTherapyTreatmentMedicineDTOs) {
        this.cmTherapyTreatmentMedicineDTOs = cmTherapyTreatmentMedicineDTOs;
    }

    public List<CmTherapyTreatmentMedicineTypeDTO> getCmTherapyTreatmentMedicineTypeDTOs() {
        return cmTherapyTreatmentMedicineTypeDTOs;
    }

    public void setCmTherapyTreatmentMedicineTypeDTOs(List<CmTherapyTreatmentMedicineTypeDTO> cmTherapyTreatmentMedicineTypeDTOs) {
        this.cmTherapyTreatmentMedicineTypeDTOs = cmTherapyTreatmentMedicineTypeDTOs;
    }
}
