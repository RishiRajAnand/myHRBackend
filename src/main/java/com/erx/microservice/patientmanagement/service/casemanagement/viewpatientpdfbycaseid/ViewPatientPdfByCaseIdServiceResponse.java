package com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid;

/*
* created by Latha on 05-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;

import java.util.List;

public class ViewPatientPdfByCaseIdServiceResponse extends CommonServiceResponse {

    private GetComplaintsDTO getComplaintsDTO;
    private List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs;
    private CmPathyaPathyaDTO cmPathyaPathyaDTO;
    private CmInvestigationGetDTO cmInvestigationGetDTO;
    private List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs;
    private List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs;
    private List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs;
    private List<GetCmExaminationDTO> getCmExaminationDTOs;

    //constructor

    public ViewPatientPdfByCaseIdServiceResponse() {
    }

    public ViewPatientPdfByCaseIdServiceResponse(GetComplaintsDTO getComplaintsDTO, List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs,
                                          CmPathyaPathyaDTO cmPathyaPathyaDTO, CmInvestigationGetDTO cmInvestigationGetDTO,
                                          List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs,
                                          List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs, List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs,
                                          List<GetCmExaminationDTO> getCmExaminationDTOs) {
        this.getComplaintsDTO = getComplaintsDTO;
        this.cmMedicineTreatmentResponseDTOs = cmMedicineTreatmentResponseDTOs;
        this.cmPathyaPathyaDTO = cmPathyaPathyaDTO;
        this.cmInvestigationGetDTO = cmInvestigationGetDTO;
        this.cmInvestigationDetailsGetDTOs = cmInvestigationDetailsGetDTOs;
        this.cmTherapyTreatmentResponseDTOs = cmTherapyTreatmentResponseDTOs;
        this.getCmPersonalHistoryDTOs = getCmPersonalHistoryDTOs;
        this.getCmExaminationDTOs = getCmExaminationDTOs;
    }

    //getters and setters

    public GetComplaintsDTO getGetComplaintsDTO() {
        return getComplaintsDTO;
    }

    public void setGetComplaintsDTO(GetComplaintsDTO getComplaintsDTO) {
        this.getComplaintsDTO = getComplaintsDTO;
    }

    public List<CmMedicineTreatmentResponseDTO> getCmMedicineTreatmentResponseDTOs() {
        return cmMedicineTreatmentResponseDTOs;
    }

    public void setCmMedicineTreatmentResponseDTOs(List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs) {
        this.cmMedicineTreatmentResponseDTOs = cmMedicineTreatmentResponseDTOs;
    }

    public CmPathyaPathyaDTO getCmPathyaPathyaDTO() {
        return cmPathyaPathyaDTO;
    }

    public void setCmPathyaPathyaDTO(CmPathyaPathyaDTO cmPathyaPathyaDTO) {
        this.cmPathyaPathyaDTO = cmPathyaPathyaDTO;
    }

    public CmInvestigationGetDTO getCmInvestigationGetDTO() {
        return cmInvestigationGetDTO;
    }

    public void setCmInvestigationGetDTO(CmInvestigationGetDTO cmInvestigationGetDTO) {
        this.cmInvestigationGetDTO = cmInvestigationGetDTO;
    }

    public List<CmInvestigationDetailsGetDTO> getCmInvestigationDetailsGetDTOs() {
        return cmInvestigationDetailsGetDTOs;
    }

    public void setCmInvestigationDetailsGetDTOs(List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs) {
        this.cmInvestigationDetailsGetDTOs = cmInvestigationDetailsGetDTOs;
    }

    public List<CmTherapyTreatmentResponseDTO> getCmTherapyTreatmentResponseDTOs() {
        return cmTherapyTreatmentResponseDTOs;
    }

    public void setCmTherapyTreatmentResponseDTOs(List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs) {
        this.cmTherapyTreatmentResponseDTOs = cmTherapyTreatmentResponseDTOs;
    }

    public List<GetCmPersonalHistoryDTO> getGetCmPersonalHistoryDTOs() {
        return getCmPersonalHistoryDTOs;
    }

    public void setGetCmPersonalHistoryDTOs(List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs) {
        this.getCmPersonalHistoryDTOs = getCmPersonalHistoryDTOs;
    }

    public List<GetCmExaminationDTO> getGetCmExaminationDTOs() {
        return getCmExaminationDTOs;
    }

    public void setGetCmExaminationDTOs(List<GetCmExaminationDTO> getCmExaminationDTOs) {
        this.getCmExaminationDTOs = getCmExaminationDTOs;
    }
}
