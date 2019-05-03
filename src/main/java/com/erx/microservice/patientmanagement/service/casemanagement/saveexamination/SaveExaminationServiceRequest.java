package com.erx.microservice.patientmanagement.service.casemanagement.saveexamination;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCmExaminationDTO;

public class SaveExaminationServiceRequest implements CommonServiceRequest {

    private SaveCmExaminationDTO saveCmExaminationDTO;

    //constructor

    public SaveExaminationServiceRequest() {
    }

    public SaveExaminationServiceRequest(SaveCmExaminationDTO saveCmExaminationDTO) {
        this.saveCmExaminationDTO = saveCmExaminationDTO;
    }

    //getters and setters

    public SaveCmExaminationDTO getSaveCmExaminationDTO() {
        return saveCmExaminationDTO;
    }

    public void setSaveCmExaminationDTO(SaveCmExaminationDTO saveCmExaminationDTO) {
        this.saveCmExaminationDTO = saveCmExaminationDTO;
    }
}
