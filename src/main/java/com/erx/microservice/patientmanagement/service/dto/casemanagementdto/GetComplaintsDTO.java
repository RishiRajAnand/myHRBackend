package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.util.List;

/**
 * Created by Latha on 20/08/18.
 */

public class GetComplaintsDTO {

    private Long patientId;
    private Long caseId;
    private String caseNumber;
    private String chiefComplaints;
    private List<CmMasterDetailsDTO> cmMasterDetailsDTOs;
    private List<CmMasterComplaintDTO> cmMasterComplaintDTOs;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getChiefComplaints() {
        return chiefComplaints;
    }

    public void setChiefComplaints(String chiefComplaints) {
        this.chiefComplaints = chiefComplaints;
    }

    public List<CmMasterDetailsDTO> getCmMasterDetailsDTOs() {
        return cmMasterDetailsDTOs;
    }

    public void setCmMasterDetailsDTOs(List<CmMasterDetailsDTO> cmMasterDetailsDTOs) {
        this.cmMasterDetailsDTOs = cmMasterDetailsDTOs;
    }

    public List<CmMasterComplaintDTO> getCmMasterComplaintDTOs() {
        return cmMasterComplaintDTOs;
    }

    public void setCmMasterComplaintDTOs(List<CmMasterComplaintDTO> cmMasterComplaintDTOs) {
        this.cmMasterComplaintDTOs = cmMasterComplaintDTOs;
    }
}
