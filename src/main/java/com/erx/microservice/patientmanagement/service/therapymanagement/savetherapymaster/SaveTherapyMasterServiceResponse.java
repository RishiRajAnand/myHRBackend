package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyMasterResponseDTO;

public class SaveTherapyMasterServiceResponse extends CommonServiceResponse {

    private SaveTherapyMasterResponseDTO saveTherapyMasterResponseDTO;
    //constructor

    public SaveTherapyMasterServiceResponse() {
    }

    public SaveTherapyMasterServiceResponse(SaveTherapyMasterResponseDTO saveTherapyMasterResponseDTO) {
        this.saveTherapyMasterResponseDTO = saveTherapyMasterResponseDTO;
    }

    //getters and setters

    public SaveTherapyMasterResponseDTO getSaveTherapyMasterResponseDTO() {
        return saveTherapyMasterResponseDTO;
    }

    public void setSaveTherapyMasterResponseDTO(SaveTherapyMasterResponseDTO saveTherapyMasterResponseDTO) {
        this.saveTherapyMasterResponseDTO = saveTherapyMasterResponseDTO;
    }
}
