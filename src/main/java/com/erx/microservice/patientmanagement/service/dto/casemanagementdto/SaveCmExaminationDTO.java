package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

public class SaveCmExaminationDTO {

    private Long cmMasterId;
    private Long cmMasterDetailId;
    private Long clinicId;
    private Long patientAppointmentId;
    private CmExamntnDashavidhaDTO cmExamntnDashavidhaDTO;
    private CmExamntnAsthaVidhaPareekshaDTO cmExamntnAsthaVidhaPareekshaDTO;
    private CmExamntnGeneralDTO cmExamntnGeneralDTO;
    private CmExamntnSarvaSrotoPareekshaDTO cmExamntnSarvaSrotoPareekshaDTO;
    private CmExamntnSampraptiGhatakasDTO cmExamntnSampraptiGhatakasDTO;

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public CmExamntnDashavidhaDTO getCmExamntnDashavidhaDTO() {
        return cmExamntnDashavidhaDTO;
    }

    public void setCmExamntnDashavidhaDTO(CmExamntnDashavidhaDTO cmExamntnDashavidhaDTO) {
        this.cmExamntnDashavidhaDTO = cmExamntnDashavidhaDTO;
    }

    public CmExamntnAsthaVidhaPareekshaDTO getCmExamntnAsthaVidhaPareekshaDTO() {
        return cmExamntnAsthaVidhaPareekshaDTO;
    }

    public void setCmExamntnAsthaVidhaPareekshaDTO(CmExamntnAsthaVidhaPareekshaDTO cmExamntnAsthaVidhaPareekshaDTO) {
        this.cmExamntnAsthaVidhaPareekshaDTO = cmExamntnAsthaVidhaPareekshaDTO;
    }

    public CmExamntnGeneralDTO getCmExamntnGeneralDTO() {
        return cmExamntnGeneralDTO;
    }

    public void setCmExamntnGeneralDTO(CmExamntnGeneralDTO cmExamntnGeneralDTO) {
        this.cmExamntnGeneralDTO = cmExamntnGeneralDTO;
    }

    public CmExamntnSarvaSrotoPareekshaDTO getCmExamntnSarvaSrotoPareekshaDTO() {
        return cmExamntnSarvaSrotoPareekshaDTO;
    }

    public void setCmExamntnSarvaSrotoPareekshaDTO(CmExamntnSarvaSrotoPareekshaDTO cmExamntnSarvaSrotoPareekshaDTO) {
        this.cmExamntnSarvaSrotoPareekshaDTO = cmExamntnSarvaSrotoPareekshaDTO;
    }

    public CmExamntnSampraptiGhatakasDTO getCmExamntnSampraptiGhatakasDTO() {
        return cmExamntnSampraptiGhatakasDTO;
    }

    public void setCmExamntnSampraptiGhatakasDTO(CmExamntnSampraptiGhatakasDTO cmExamntnSampraptiGhatakasDTO) {
        this.cmExamntnSampraptiGhatakasDTO = cmExamntnSampraptiGhatakasDTO;
    }
}
