package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 01-10-2018
* */

public class SaveCmMedicineTreatmentDTO {

    private Long cmTreatmentId;
    private TreatmentPrincipleDTO treatmentPrincipleDTO;
    private PathyaPathyaDTO pathyaPathyaDTO;

    public Long getCmTreatmentId() {
        return cmTreatmentId;
    }

    public void setCmTreatmentId(Long cmTreatmentId) {
        this.cmTreatmentId = cmTreatmentId;
    }

    public TreatmentPrincipleDTO getTreatmentPrincipleDTO() {
        return treatmentPrincipleDTO;
    }

    public void setTreatmentPrincipleDTO(TreatmentPrincipleDTO treatmentPrincipleDTO) {
        this.treatmentPrincipleDTO = treatmentPrincipleDTO;
    }

    public PathyaPathyaDTO getPathyaPathyaDTO() {
        return pathyaPathyaDTO;
    }

    public void setPathyaPathyaDTO(PathyaPathyaDTO pathyaPathyaDTO) {
        this.pathyaPathyaDTO = pathyaPathyaDTO;
    }

}
