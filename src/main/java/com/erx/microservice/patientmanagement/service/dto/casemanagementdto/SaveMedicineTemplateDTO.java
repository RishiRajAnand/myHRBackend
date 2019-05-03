package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 26-08-2018
* */

public class SaveMedicineTemplateDTO {

    private Long cmTemplateId;
    private Long clinicId;
    private String name;
    private String instructions;
    private Long scienceOfMedicineId;
    private String description;
    private TemplateMedicineDTO templateMedicineDTO;
    private SaveTemplateGroupDTO saveTemplateGroupDTO;

    public Long getCmTemplateId() {
        return cmTemplateId;
    }

    public void setCmTemplateId(Long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Long getScienceOfMedicineId() {
        return scienceOfMedicineId;
    }

    public void setScienceOfMedicineId(Long scienceOfMedicineId) {
        this.scienceOfMedicineId = scienceOfMedicineId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplateMedicineDTO getTemplateMedicineDTO() {
        return templateMedicineDTO;
    }

    public void setTemplateMedicineDTO(TemplateMedicineDTO templateMedicineDTO) {
        this.templateMedicineDTO = templateMedicineDTO;
    }

    public SaveTemplateGroupDTO getSaveTemplateGroupDTO() {
        return saveTemplateGroupDTO;
    }

    public void setSaveTemplateGroupDTO(SaveTemplateGroupDTO saveTemplateGroupDTO) {
        this.saveTemplateGroupDTO = saveTemplateGroupDTO;
    }
}
