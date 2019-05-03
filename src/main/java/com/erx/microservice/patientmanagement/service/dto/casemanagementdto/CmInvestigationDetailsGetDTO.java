package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.util.List;

/**
 * Created by Latha on 18/09/18.
 */

public class CmInvestigationDetailsGetDTO {

    private Long cmInvestigationDetailId;
    private Long cmInvestigationId;
    private String labOrderNumber;
    private String labOrderGivenDate;
    private List<CmInvestigationLabDetailGetDTO> cmInvestigationLabDetailGetDTOs;

    public Long getCmInvestigationDetailId() {
        return cmInvestigationDetailId;
    }

    public void setCmInvestigationDetailId(Long cmInvestigationDetailId) {
        this.cmInvestigationDetailId = cmInvestigationDetailId;
    }

    public Long getCmInvestigationId() {
        return cmInvestigationId;
    }

    public void setCmInvestigationId(Long cmInvestigationId) {
        this.cmInvestigationId = cmInvestigationId;
    }

    public String getLabOrderNumber() {
        return labOrderNumber;
    }

    public void setLabOrderNumber(String labOrderNumber) {
        this.labOrderNumber = labOrderNumber;
    }

    public String getLabOrderGivenDate() {
        return labOrderGivenDate;
    }

    public void setLabOrderGivenDate(String labOrderGivenDate) {
        this.labOrderGivenDate = labOrderGivenDate;
    }

    public List<CmInvestigationLabDetailGetDTO> getCmInvestigationLabDetailGetDTOs() {
        return cmInvestigationLabDetailGetDTOs;
    }

    public void setCmInvestigationLabDetailGetDTOs(List<CmInvestigationLabDetailGetDTO> cmInvestigationLabDetailGetDTOs) {
        this.cmInvestigationLabDetailGetDTOs = cmInvestigationLabDetailGetDTOs;
    }
}
