package com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyResponseDTO;

public class SaveCaseDischargeServiceResponse extends CommonServiceResponse {

    private Long dischargeRequestId;
    private Long bmOrderId;
    private String bmOrderNumber;
    private SaveMedicineResponseDTO saveMedicineResponseDTO;
    private SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO;

    //constructor

    public SaveCaseDischargeServiceResponse(Long dischargeRequestId, Long bmOrderId, String bmOrderNumber,
                                            SaveMedicineResponseDTO saveMedicineResponseDTO, SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO) {
        this.dischargeRequestId = dischargeRequestId;
        this.bmOrderId = bmOrderId;
        this.bmOrderNumber = bmOrderNumber;
        this.saveMedicineResponseDTO = saveMedicineResponseDTO;
        this.saveTreatmentTherapyResponseDTO = saveTreatmentTherapyResponseDTO;
    }

    public SaveCaseDischargeServiceResponse() {
    }

    //getters and setters

    public Long getDischargeRequestId() {
        return dischargeRequestId;
    }

    public void setDischargeRequestId(Long dischargeRequestId) {
        this.dischargeRequestId = dischargeRequestId;
    }

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
