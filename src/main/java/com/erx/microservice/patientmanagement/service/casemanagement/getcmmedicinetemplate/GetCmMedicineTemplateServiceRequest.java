package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetCmMedicineTemplateServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long scienceOfMedicineId;

    //constructor

    public GetCmMedicineTemplateServiceRequest(Long clinicId, Long scienceOfMedicineId) {
        this.clinicId = clinicId;
        this.scienceOfMedicineId = scienceOfMedicineId;
    }

    public GetCmMedicineTemplateServiceRequest() {
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getScienceOfMedicineId() {
        return scienceOfMedicineId;
    }

    public void setScienceOfMedicineId(Long scienceOfMedicineId) {
        this.scienceOfMedicineId = scienceOfMedicineId;
    }
}
