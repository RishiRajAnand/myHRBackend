package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails;

/*
* created by Latha on 01-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCmInvestigationResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyResponseDTO;


public class SaveInvestigationAndTreatmentDetailsServiceResponse extends CommonServiceResponse {

    private Long bmOrderId;
    private String bmOrderNumber;
    private SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO;
    private SaveMedicineResponseDTO saveMedicineResponseDTO;
    private SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO;

    //constructor

    public SaveInvestigationAndTreatmentDetailsServiceResponse() {
    }

    public SaveInvestigationAndTreatmentDetailsServiceResponse(Long bmOrderId, String bmOrderNumber, SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO,
                                                               SaveMedicineResponseDTO saveMedicineResponseDTO, SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO) {
        this.bmOrderId = bmOrderId;
        this.bmOrderNumber = bmOrderNumber;
        this.saveCmInvestigationResponseDTO = saveCmInvestigationResponseDTO;
        this.saveMedicineResponseDTO = saveMedicineResponseDTO;
        this.saveTreatmentTherapyResponseDTO = saveTreatmentTherapyResponseDTO;
    }

    //getters and setters

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public String getBmOrderNumber() {
        return bmOrderNumber;
    }

    public void setBmOrderNumber(String bmOrderNumber) {
        this.bmOrderNumber = bmOrderNumber;
    }

    public SaveCmInvestigationResponseDTO getSaveCmInvestigationResponseDTO() {
        return saveCmInvestigationResponseDTO;
    }

    public void setSaveCmInvestigationResponseDTO(SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO) {
        this.saveCmInvestigationResponseDTO = saveCmInvestigationResponseDTO;
    }

    public SaveMedicineResponseDTO getSaveMedicineResponseDTO() {
        return saveMedicineResponseDTO;
    }

    public void setSaveMedicineResponseDTO(SaveMedicineResponseDTO saveMedicineResponseDTO) {
        this.saveMedicineResponseDTO = saveMedicineResponseDTO;
    }

    public SaveTreatmentTherapyResponseDTO getSaveTreatmentTherapyResponseDTO() {
        return saveTreatmentTherapyResponseDTO;
    }

    public void setSaveTreatmentTherapyResponseDTO(SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO) {
        this.saveTreatmentTherapyResponseDTO = saveTreatmentTherapyResponseDTO;
    }
}
