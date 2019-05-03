package com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteTreatmentTherapyServiceRequest implements CommonServiceRequest {

    private Long therapyPlanningId;

    //constructor
    public DeleteTreatmentTherapyServiceRequest() {
    }

    public DeleteTreatmentTherapyServiceRequest(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
    }

    //getters and setters


    public Long getTherapyPlanningId() {
        return therapyPlanningId;
    }

    public void setTherapyPlanningId(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
    }
}
