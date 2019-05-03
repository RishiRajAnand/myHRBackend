package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalTime;

/**
 * Created by Latha on 24/08/18.
 */

public class CmDosageInstructionDTO {

    private Long cmDosageInstructionId;
    private String cmDosageInstruction;
    private LocalTime cmDosageDefaultTime;

    public Long getCmDosageInstructionId() {
        return cmDosageInstructionId;
    }

    public void setCmDosageInstructionId(Long cmDosageInstructionId) {
        this.cmDosageInstructionId = cmDosageInstructionId;
    }

    public String getCmDosageInstruction() {
        return cmDosageInstruction;
    }

    public void setCmDosageInstruction(String cmDosageInstruction) {
        this.cmDosageInstruction = cmDosageInstruction;
    }

    public LocalTime getCmDosageDefaultTime() {
        return cmDosageDefaultTime;
    }

    public void setCmDosageDefaultTime(LocalTime cmDosageDefaultTime) {
        this.cmDosageDefaultTime = cmDosageDefaultTime;
    }
}
