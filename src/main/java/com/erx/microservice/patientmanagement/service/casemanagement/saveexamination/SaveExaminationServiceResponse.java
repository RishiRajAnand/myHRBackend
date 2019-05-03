package com.erx.microservice.patientmanagement.service.casemanagement.saveexamination;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmExaminationResponseDTO;

public class SaveExaminationServiceResponse extends CommonServiceResponse {

    private CmExaminationResponseDTO cmExaminationResponseDTO;

    //constructor

    public SaveExaminationServiceResponse(CmExaminationResponseDTO cmExaminationResponseDTO) {
        this.cmExaminationResponseDTO = cmExaminationResponseDTO;
    }

    public SaveExaminationServiceResponse() {
    }

    //getters and setters

    public CmExaminationResponseDTO getCmExaminationResponseDTO() {
        return cmExaminationResponseDTO;
    }

    public void setCmExaminationResponseDTO(CmExaminationResponseDTO cmExaminationResponseDTO) {
        this.cmExaminationResponseDTO = cmExaminationResponseDTO;
    }
}
