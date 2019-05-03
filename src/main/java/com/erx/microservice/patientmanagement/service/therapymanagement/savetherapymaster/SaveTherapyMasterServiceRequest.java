package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyMasterDTO;

public class SaveTherapyMasterServiceRequest implements CommonServiceRequest {

    private SaveTherapyMasterDTO saveTherapyMasterDTO;

    //constructor

    public SaveTherapyMasterServiceRequest(SaveTherapyMasterDTO saveTherapyMasterDTO) {
        this.saveTherapyMasterDTO = saveTherapyMasterDTO;
    }

    public SaveTherapyMasterServiceRequest() {
    }

    //getters and setters

    public SaveTherapyMasterDTO getSaveTherapyMasterDTO() {
        return saveTherapyMasterDTO;
    }

    public void setSaveTherapyMasterDTO(SaveTherapyMasterDTO saveTherapyMasterDTO) {
        this.saveTherapyMasterDTO = saveTherapyMasterDTO;
    }
}
