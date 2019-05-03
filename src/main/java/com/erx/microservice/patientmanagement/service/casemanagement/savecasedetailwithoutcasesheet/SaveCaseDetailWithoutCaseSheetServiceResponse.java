package com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet;
/*
 * created by Raushan on 28-08-2018
 * */
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;

import java.util.List;

public class SaveCaseDetailWithoutCaseSheetServiceResponse extends CommonServiceResponse {
    private  List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOs;
    //Constructor

    public SaveCaseDetailWithoutCaseSheetServiceResponse(List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOs) {
        this.createBillingOrderServiceDTOs = createBillingOrderServiceDTOs;
    }

    public SaveCaseDetailWithoutCaseSheetServiceResponse() {
    }
    //getter and setter


    public List<CreateBillingOrderServiceDTO> getCreateBillingOrderServiceDTOs() {
        return createBillingOrderServiceDTOs;
    }

    public void setCreateBillingOrderServiceDTOs(List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOs) {
        this.createBillingOrderServiceDTOs = createBillingOrderServiceDTOs;
    }
}