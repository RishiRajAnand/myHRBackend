package com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.DeleteMedicineTreatmentDTO;

public class DeleteMedicineTreatmentServiceRequest implements CommonServiceRequest {

    private DeleteMedicineTreatmentDTO deleteMedicineTreatmentDTO;

    //constructor

    public DeleteMedicineTreatmentServiceRequest(DeleteMedicineTreatmentDTO deleteMedicineTreatmentDTO) {
        this.deleteMedicineTreatmentDTO = deleteMedicineTreatmentDTO;
    }

    public DeleteMedicineTreatmentServiceRequest() {
    }

    //getters and setters

    public DeleteMedicineTreatmentDTO getDeleteMedicineTreatmentDTO() {
        return deleteMedicineTreatmentDTO;
    }

    public void setDeleteMedicineTreatmentDTO(DeleteMedicineTreatmentDTO deleteMedicineTreatmentDTO) {
        this.deleteMedicineTreatmentDTO = deleteMedicineTreatmentDTO;
    }
}
