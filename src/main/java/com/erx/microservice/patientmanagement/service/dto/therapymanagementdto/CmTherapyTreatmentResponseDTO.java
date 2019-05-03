package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

import java.time.LocalDateTime;
import java.util.List;

public class CmTherapyTreatmentResponseDTO {

    private LocalDateTime therapyDate;
    private List<CmTherapyTreatmentDetailsDTO> cmTherapyTreatmentDetailsDTOs;

    public LocalDateTime getTherapyDate() {
        return therapyDate;
    }

    public void setTherapyDate(LocalDateTime therapyDate) {
        this.therapyDate = therapyDate;
    }

    public List<CmTherapyTreatmentDetailsDTO> getCmTherapyTreatmentDetailsDTOs() {
        return cmTherapyTreatmentDetailsDTOs;
    }

    public void setCmTherapyTreatmentDetailsDTOs(List<CmTherapyTreatmentDetailsDTO> cmTherapyTreatmentDetailsDTOs) {
        this.cmTherapyTreatmentDetailsDTOs = cmTherapyTreatmentDetailsDTOs;
    }
}
