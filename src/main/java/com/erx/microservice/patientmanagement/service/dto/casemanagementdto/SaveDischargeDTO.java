package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Latha on 06/10/18.
 */

public class SaveDischargeDTO {

    private Long dischargeRequestId;
    private LocalDate dischargeRequestDate;
    private LocalTime dischargeRequestTime;
    private String adviceOnDischarge;
    private SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO;

    public Long getDischargeRequestId() {
        return dischargeRequestId;
    }

    public void setDischargeRequestId(Long dischargeRequestId) {
        this.dischargeRequestId = dischargeRequestId;
    }

    public LocalDate getDischargeRequestDate() {
        return dischargeRequestDate;
    }

    public void setDischargeRequestDate(LocalDate dischargeRequestDate) {
        this.dischargeRequestDate = dischargeRequestDate;
    }

    public LocalTime getDischargeRequestTime() {
        return dischargeRequestTime;
    }

    public void setDischargeRequestTime(LocalTime dischargeRequestTime) {
        this.dischargeRequestTime = dischargeRequestTime;
    }

    public SaveInvestigationAndTreatmentDTO getSaveInvestigationAndTreatmentDTO() {
        return saveInvestigationAndTreatmentDTO;
    }

    public void setSaveInvestigationAndTreatmentDTO(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO) {
        this.saveInvestigationAndTreatmentDTO = saveInvestigationAndTreatmentDTO;
    }

    public String getAdviceOnDischarge() {
        return adviceOnDischarge;
    }

    public void setAdviceOnDischarge(String adviceOnDischarge) {
        this.adviceOnDischarge = adviceOnDischarge;
    }
}
