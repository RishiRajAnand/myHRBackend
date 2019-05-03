package com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail;

/*
* created by raushan on 24-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveTherapyPlanningDTO;

import java.util.List;

public class SaveTherapyMedicineDetailServiceRequest implements CommonServiceRequest {

    private List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs;
    private Long caseId;
    private Long userId;

    //constructor

    public SaveTherapyMedicineDetailServiceRequest() {
    }

    public SaveTherapyMedicineDetailServiceRequest(List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs, Long caseId, Long userId) {
        this.saveTherapyPlanningDTOs = saveTherapyPlanningDTOs;
        this.caseId = caseId;
        this.userId = userId;
    }

    //getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public List<SaveTherapyPlanningDTO> getSaveTherapyPlanningDTOs() {
        return saveTherapyPlanningDTOs;
    }

    public void setSaveTherapyPlanningDTOs(List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs) {
        this.saveTherapyPlanningDTOs = saveTherapyPlanningDTOs;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
