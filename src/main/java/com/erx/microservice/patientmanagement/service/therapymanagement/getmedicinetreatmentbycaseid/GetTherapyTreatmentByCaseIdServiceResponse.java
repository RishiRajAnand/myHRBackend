package com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMedicineTreatmentResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;

import java.util.List;

public class GetTherapyTreatmentByCaseIdServiceResponse extends CommonServiceResponse {

    private List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs;

    //constructor

    public GetTherapyTreatmentByCaseIdServiceResponse() {
    }

    public GetTherapyTreatmentByCaseIdServiceResponse(List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs) {
        this.cmTherapyTreatmentResponseDTOs = cmTherapyTreatmentResponseDTOs;
    }

    //getters and setters

    public List<CmTherapyTreatmentResponseDTO> getCmTherapyTreatmentResponseDTOs() {
        return cmTherapyTreatmentResponseDTOs;
    }

    public void setCmTherapyTreatmentResponseDTOs(List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs) {
        this.cmTherapyTreatmentResponseDTOs = cmTherapyTreatmentResponseDTOs;
    }
}
