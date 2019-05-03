package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 02-09-2018
* */

import java.time.LocalDateTime;
import java.util.List;

public class CmMedicineTreatmentResponseDTO {

    private LocalDateTime medicineTreatmentGivenDate;
    private List<CmIndividualMedicineTreatmentDTO> cmIndividualMedicineTreatmentDTOs;
    private List<CmTreatmentGroupDTO> cmTreatmentGroupDTOs;

    public LocalDateTime getMedicineTreatmentGivenDate() {
        return medicineTreatmentGivenDate;
    }

    public void setMedicineTreatmentGivenDate(LocalDateTime medicineTreatmentGivenDate) {
        this.medicineTreatmentGivenDate = medicineTreatmentGivenDate;
    }

    public List<CmIndividualMedicineTreatmentDTO> getCmIndividualMedicineTreatmentDTOs() {
        return cmIndividualMedicineTreatmentDTOs;
    }

    public void setCmIndividualMedicineTreatmentDTOs(List<CmIndividualMedicineTreatmentDTO> cmIndividualMedicineTreatmentDTOs) {
        this.cmIndividualMedicineTreatmentDTOs = cmIndividualMedicineTreatmentDTOs;
    }

    public List<CmTreatmentGroupDTO> getCmTreatmentGroupDTOs() {
        return cmTreatmentGroupDTOs;
    }

    public void setCmTreatmentGroupDTOs(List<CmTreatmentGroupDTO> cmTreatmentGroupDTOs) {
        this.cmTreatmentGroupDTOs = cmTreatmentGroupDTOs;
    }
}
