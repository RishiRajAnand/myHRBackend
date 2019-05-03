package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 27-08-2018
* */


import java.util.List;

public class CmTemplateResponseDTO {

    private long cmTemplateId;
    private String name;
    private String instruction;
    private List<CmTemplateMedicineDTO> cmTemplateMedicineDTOs;
    private List<CmTemplateGroupMedicineDTO> cmTemplateGroupMedicineDTOs;

    public long getCmTemplateId() {
        return cmTemplateId;
    }

    public void setCmTemplateId(long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<CmTemplateMedicineDTO> getCmTemplateMedicineDTOs() {
        return cmTemplateMedicineDTOs;
    }

    public void setCmTemplateMedicineDTOs(List<CmTemplateMedicineDTO> cmTemplateMedicineDTOs) {
        this.cmTemplateMedicineDTOs = cmTemplateMedicineDTOs;
    }

    public List<CmTemplateGroupMedicineDTO> getCmTemplateGroupMedicineDTOs() {
        return cmTemplateGroupMedicineDTOs;
    }

    public void setCmTemplateGroupMedicineDTOs(List<CmTemplateGroupMedicineDTO> cmTemplateGroupMedicineDTOs) {
        this.cmTemplateGroupMedicineDTOs = cmTemplateGroupMedicineDTOs;
    }
}
