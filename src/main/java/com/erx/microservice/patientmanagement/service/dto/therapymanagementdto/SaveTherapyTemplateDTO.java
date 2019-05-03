package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

public class SaveTherapyTemplateDTO {

    private Long therapyTemplateId;
    private Long clinicId;
    private String therapyGroupName;
    private String name;
    private String specialInstruction;
    private SaveTherapyTemplateMappingDTO saveTherapyTemplateMappingDTO;

    public Long getTherapyTemplateId() {
        return therapyTemplateId;
    }

    public void setTherapyTemplateId(Long therapyTemplateId) {
        this.therapyTemplateId = therapyTemplateId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getTherapyGroupName() {
        return therapyGroupName;
    }

    public void setTherapyGroupName(String therapyGroupName) {
        this.therapyGroupName = therapyGroupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public SaveTherapyTemplateMappingDTO getSaveTherapyTemplateMappingDTO() {
        return saveTherapyTemplateMappingDTO;
    }

    public void setSaveTherapyTemplateMappingDTO(SaveTherapyTemplateMappingDTO saveTherapyTemplateMappingDTO) {
        this.saveTherapyTemplateMappingDTO = saveTherapyTemplateMappingDTO;
    }
}
