package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

public class DosageTimeDTO {

    private Long sequenceNo;
    private String time;
    private String dosageInstructionId;
    private String dosageInstructionName;

    public Long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getDosageInstructionId() {
        return dosageInstructionId;
    }

    public void setDosageInstructionId(String dosageInstructionId) {
        this.dosageInstructionId = dosageInstructionId;
    }

    public String getDosageInstructionName() {
        return dosageInstructionName;
    }

    public void setDosageInstructionName(String dosageInstructionName) {
        this.dosageInstructionName = dosageInstructionName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "sequenceNo=" + sequenceNo +
                ", time='" + time + '\'' +
                ", dosageInstructionId=" + dosageInstructionId +
                ", dosageInstructionName='" + dosageInstructionName + '\'' +
                '}';
    }
}
