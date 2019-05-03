package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveMedicineTemplateServiceResponse extends CommonServiceResponse {

    private Long cmTemplateMedicineId;

    //constructor

    public SaveMedicineTemplateServiceResponse(Long cmTemplateMedicineId) {
        this.cmTemplateMedicineId = cmTemplateMedicineId;
    }

    public SaveMedicineTemplateServiceResponse() {
    }

    //getters and setters

    public Long getCmTemplateMedicineId() {
        return cmTemplateMedicineId;
    }

    public void setCmTemplateMedicineId(Long cmTemplateMedicineId) {
        this.cmTemplateMedicineId = cmTemplateMedicineId;
    }
}
