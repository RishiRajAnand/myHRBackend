package com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMedicineTreatmentResponseDTO;

import java.util.List;

public class GetMedicineTreatmentByCaseIdServiceResponse extends CommonServiceResponse {

    private List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs;

    //constructor

    public GetMedicineTreatmentByCaseIdServiceResponse(List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs) {
        this.cmMedicineTreatmentResponseDTOs = cmMedicineTreatmentResponseDTOs;
    }

    public GetMedicineTreatmentByCaseIdServiceResponse() {
    }

    //getters and setters

    public List<CmMedicineTreatmentResponseDTO> getCmMedicineTreatmentResponseDTOs() {
        return cmMedicineTreatmentResponseDTOs;
    }

    public void setCmMedicineTreatmentResponseDTOs(List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs) {
        this.cmMedicineTreatmentResponseDTOs = cmMedicineTreatmentResponseDTOs;
    }
}
