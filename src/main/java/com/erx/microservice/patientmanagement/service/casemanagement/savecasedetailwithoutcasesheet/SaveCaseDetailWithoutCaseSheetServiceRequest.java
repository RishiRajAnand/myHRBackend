package com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet;
/*
 * created by Raushan on 28-08-2018
 * */
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCaseDetailWithoutCaseSheetDTO;

public class SaveCaseDetailWithoutCaseSheetServiceRequest implements CommonServiceRequest {
    private SaveCaseDetailWithoutCaseSheetDTO  saveCaseDetailWithoutCaseSheetDTO;

    //Constructor
    public SaveCaseDetailWithoutCaseSheetServiceRequest() {
    }

    public SaveCaseDetailWithoutCaseSheetServiceRequest(SaveCaseDetailWithoutCaseSheetDTO saveCaseDetailWithoutCaseSheetDTO) {
        this.saveCaseDetailWithoutCaseSheetDTO = saveCaseDetailWithoutCaseSheetDTO;
    }
    //getter and setter


    public SaveCaseDetailWithoutCaseSheetDTO getSaveCaseDetailWithoutCaseSheetDTO() {
        return saveCaseDetailWithoutCaseSheetDTO;
    }

    public void setSaveCaseDetailWithoutCaseSheetDTO(SaveCaseDetailWithoutCaseSheetDTO saveCaseDetailWithoutCaseSheetDTO) {
        this.saveCaseDetailWithoutCaseSheetDTO = saveCaseDetailWithoutCaseSheetDTO;
    }
}
