package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 26-08-2018
* */

import java.util.List;

public class SaveTemplateGroupDTO {

    private TemplateMedicineDTO templateGroupInfoMedicineDTO;
    private List<TemplateGroupIndividualMedicineDTO> templateGroupIndividualMedicineDTOs;

    public TemplateMedicineDTO getTemplateGroupInfoMedicineDTO() {
        return templateGroupInfoMedicineDTO;
    }

    public void setTemplateGroupInfoMedicineDTO(TemplateMedicineDTO templateGroupInfoMedicineDTO) {
        this.templateGroupInfoMedicineDTO = templateGroupInfoMedicineDTO;
    }

    public List<TemplateGroupIndividualMedicineDTO> getTemplateGroupIndividualMedicineDTOs() {
        return templateGroupIndividualMedicineDTOs;
    }

    public void setTemplateGroupIndividualMedicineDTOs(List<TemplateGroupIndividualMedicineDTO> templateGroupIndividualMedicineDTOs) {
        this.templateGroupIndividualMedicineDTOs = templateGroupIndividualMedicineDTOs;
    }
}
