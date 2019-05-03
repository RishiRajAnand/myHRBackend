package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetCmMedicineTemplateByIdServiceRequest implements CommonServiceRequest {

    private Long cmTemplateId;

    //constructor

    public GetCmMedicineTemplateByIdServiceRequest(Long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }

    public GetCmMedicineTemplateByIdServiceRequest() {
    }

    //getters and setters

    public Long getCmTemplateId() {
        return cmTemplateId;
    }

    public void setCmTemplateId(Long cmTemplateId) {
        this.cmTemplateId = cmTemplateId;
    }
}
