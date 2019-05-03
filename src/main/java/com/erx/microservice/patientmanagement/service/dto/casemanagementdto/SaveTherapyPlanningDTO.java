package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 10-09-2018
* */

import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineTypeDTO;

import java.util.List;

public class SaveTherapyPlanningDTO {

    private Long bmOrderId;
    private Long therapyPlanningId;
    private Long serviceCatalogueId;
    private Long serviceStatus; // 0- Not Changed, 1- Added, 2- Updated, 3- Deleted
    private int numberOfDays;
    private String instructions;
    private long periodicInterval; // repeat after
    private List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs;
    private List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Long serviceStatus) {
        this.serviceStatus = serviceStatus;
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

    public List<SaveTreatmentTherapyMedicineTypeDTO> getSaveTreatmentTherapyMedicineTypeDTOs() {
        return saveTreatmentTherapyMedicineTypeDTOs;
    }

    public void setSaveTreatmentTherapyMedicineTypeDTOs(List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs) {
        this.saveTreatmentTherapyMedicineTypeDTOs = saveTreatmentTherapyMedicineTypeDTOs;
    }

    public List<SaveTreatmentTherapyMedicineDTO> getSaveTreatmentTherapyMedicineDTOs() {
        return saveTreatmentTherapyMedicineDTOs;
    }

    public void setSaveTreatmentTherapyMedicineDTOs(List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs) {
        this.saveTreatmentTherapyMedicineDTOs = saveTreatmentTherapyMedicineDTOs;
    }
}
