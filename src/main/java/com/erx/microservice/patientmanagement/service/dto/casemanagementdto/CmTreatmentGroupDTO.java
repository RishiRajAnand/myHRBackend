package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 02-09-2018
* */

import java.util.List;

public class CmTreatmentGroupDTO {

    private CmIndividualMedicineTreatmentDTO cmIndividualMedicineGroupInfoDTO;
    private List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs;

    public CmIndividualMedicineTreatmentDTO getCmIndividualMedicineGroupInfoDTO() {
        return cmIndividualMedicineGroupInfoDTO;
    }

    public void setCmIndividualMedicineGroupInfoDTO(CmIndividualMedicineTreatmentDTO cmIndividualMedicineGroupInfoDTO) {
        this.cmIndividualMedicineGroupInfoDTO = cmIndividualMedicineGroupInfoDTO;
    }

    public List<CmTreatmentGroupMedicinesDTO> getCmTreatmentGroupMedicinesDTOs() {
        return cmTreatmentGroupMedicinesDTOs;
    }

    public void setCmTreatmentGroupMedicinesDTOs(List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs) {
        this.cmTreatmentGroupMedicinesDTOs = cmTreatmentGroupMedicinesDTOs;
    }
}
