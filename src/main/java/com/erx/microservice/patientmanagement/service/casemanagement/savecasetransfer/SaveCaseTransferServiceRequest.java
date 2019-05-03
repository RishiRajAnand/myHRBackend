package com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CaseTransferRequestDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmNextVisitDTO;

public class SaveCaseTransferServiceRequest implements CommonServiceRequest {

    private CaseTransferRequestDTO caseTransferRequestDTO;

    //constructor

    public SaveCaseTransferServiceRequest() {
    }

    public SaveCaseTransferServiceRequest(CaseTransferRequestDTO caseTransferRequestDTO) {
        this.caseTransferRequestDTO = caseTransferRequestDTO;
    }

    //setters and getters

    public CaseTransferRequestDTO getCaseTransferRequestDTO() {
        return caseTransferRequestDTO;
    }

    public void setCaseTransferRequestDTO(CaseTransferRequestDTO caseTransferRequestDTO) {
        this.caseTransferRequestDTO = caseTransferRequestDTO;
    }
}
