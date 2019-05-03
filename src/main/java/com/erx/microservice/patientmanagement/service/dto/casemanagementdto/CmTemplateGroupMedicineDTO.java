package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 27-08-2018
* */

import java.util.List;

public class CmTemplateGroupMedicineDTO {

    private CmTemplateMedicineDTO cmTemplateMedicineGroupInfoDTO;
    private List<CmTemplateGroupMedicinesDTO> cmTemplateGroupMedicinesDTOs;

    public CmTemplateMedicineDTO getCmTemplateMedicineGroupInfoDTO() {
        return cmTemplateMedicineGroupInfoDTO;
    }

    public void setCmTemplateMedicineGroupInfoDTO(CmTemplateMedicineDTO cmTemplateMedicineGroupInfoDTO) {
        this.cmTemplateMedicineGroupInfoDTO = cmTemplateMedicineGroupInfoDTO;
    }

    public List<CmTemplateGroupMedicinesDTO> getCmTemplateGroupMedicinesDTOs() {
        return cmTemplateGroupMedicinesDTOs;
    }

    public void setCmTemplateGroupMedicinesDTOs(List<CmTemplateGroupMedicinesDTO> cmTemplateGroupMedicinesDTOs) {
        this.cmTemplateGroupMedicinesDTOs = cmTemplateGroupMedicinesDTOs;
    }
}
