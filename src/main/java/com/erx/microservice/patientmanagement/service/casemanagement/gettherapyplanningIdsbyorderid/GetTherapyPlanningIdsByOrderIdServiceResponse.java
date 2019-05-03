package com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid;

/*
* created by Raushan on 25-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

import java.util.List;
import java.util.Set;

public class GetTherapyPlanningIdsByOrderIdServiceResponse extends CommonServiceResponse {

    private Set<Long> therapyPlanningIds;

    //constructor

    public GetTherapyPlanningIdsByOrderIdServiceResponse() {
    }

    public GetTherapyPlanningIdsByOrderIdServiceResponse(Set<Long> therapyPlanningIds) {
        this.therapyPlanningIds = therapyPlanningIds;
    }

    //getters and setters


    public Set<Long> getTherapyPlanningIds() {
        return therapyPlanningIds;
    }

    public void setTherapyPlanningIds(Set<Long> therapyPlanningIds) {
        this.therapyPlanningIds = therapyPlanningIds;
    }
}
