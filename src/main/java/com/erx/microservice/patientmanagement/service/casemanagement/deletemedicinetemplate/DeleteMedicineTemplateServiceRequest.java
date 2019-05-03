package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteMedicineTemplateServiceRequest implements CommonServiceRequest {

    private Long cmTemplateId;

    //constructor


    public DeleteMedicineTemplateServiceRequest(Long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }

    public DeleteMedicineTemplateServiceRequest() {
    }

    //getters and setters

    public Long getCmTemplateId() {
        return cmTemplateId;
    }

    public void setCmTemplateId(Long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }
}
