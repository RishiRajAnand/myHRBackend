package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

import java.util.List;

public class TherapyTemplateGetDTO {

    private long therapyTemplateId;
    private String name;
    private String therapyGroupName;
    //private String specialInstruction;
    private List<TherapyTemplateMappingGetDTO> therapyTemplateMappingGetDTOs;


    public long getTherapyTemplateId() {
        return therapyTemplateId;
    }

    public void setTherapyTemplateId(long therapyTemplateId) {
        this.therapyTemplateId = therapyTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTherapyGroupName() {
        return therapyGroupName;
    }

    public void setTherapyGroupName(String therapyGroupName) {
        this.therapyGroupName = therapyGroupName;
    }

  /*  public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }*/

    public List<TherapyTemplateMappingGetDTO> getTherapyTemplateMappingGetDTOs() {
        return therapyTemplateMappingGetDTOs;
    }

    public void setTherapyTemplateMappingGetDTOs(List<TherapyTemplateMappingGetDTO> therapyTemplateMappingGetDTOs) {
        this.therapyTemplateMappingGetDTOs = therapyTemplateMappingGetDTOs;
    }
}
