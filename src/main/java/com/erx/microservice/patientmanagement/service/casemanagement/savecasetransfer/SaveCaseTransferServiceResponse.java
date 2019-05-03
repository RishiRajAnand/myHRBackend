package com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveCaseTransferServiceResponse extends CommonServiceResponse {

    // saveStatus = 0 means success
    // saveStatus = 1 means error
    private int saveStatus;
    private Long cmCaseTransferHistoryId;
    private Long bmOrderId;

    //constructor

    public SaveCaseTransferServiceResponse() {
    }

    public SaveCaseTransferServiceResponse(int saveStatus, Long cmCaseTransferHistoryId, Long bmOrderId) {
        this.saveStatus = saveStatus;
        this.cmCaseTransferHistoryId = cmCaseTransferHistoryId;
        this.bmOrderId = bmOrderId;
    }

    //getters and setters


    public int getSaveStatus() {
        return saveStatus;
    }

    public void setSaveStatus(int saveStatus) {
        this.saveStatus = saveStatus;
    }

    public Long getCmCaseTransferHistoryId() {
        return cmCaseTransferHistoryId;
    }

    public void setCmCaseTransferHistoryId(Long cmCaseTransferHistoryId) {
        this.cmCaseTransferHistoryId = cmCaseTransferHistoryId;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }
}
