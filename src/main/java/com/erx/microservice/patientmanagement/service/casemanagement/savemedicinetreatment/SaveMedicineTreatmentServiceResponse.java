package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineResponseDTO;

public class SaveMedicineTreatmentServiceResponse extends CommonServiceResponse {

    private SaveMedicineResponseDTO saveMedicineResponseDTO;

    //constructor

    public SaveMedicineTreatmentServiceResponse(SaveMedicineResponseDTO saveMedicineResponseDTO) {
        this.saveMedicineResponseDTO = saveMedicineResponseDTO;
    }

    public SaveMedicineTreatmentServiceResponse() {
    }

    //getters and setters

    public SaveMedicineResponseDTO getSaveMedicineResponseDTO() {
        return saveMedicineResponseDTO;
    }

    public void setSaveMedicineResponseDTO(SaveMedicineResponseDTO saveMedicineResponseDTO) {
        this.saveMedicineResponseDTO = saveMedicineResponseDTO;
    }
}
