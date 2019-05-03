package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.util.List;

/**
 * Created by Latha on 18/09/18.
 */

public class CmInvestigationLabDetailGetDTO {

    private String department;
    private List<CmInvestigationLabItemDetailGetDTO> cmInvestigationLabItemDetailGetDTOs;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<CmInvestigationLabItemDetailGetDTO> getCmInvestigationLabItemDetailGetDTOs() {
        return cmInvestigationLabItemDetailGetDTOs;
    }

    public void setCmInvestigationLabItemDetailGetDTOs(List<CmInvestigationLabItemDetailGetDTO> cmInvestigationLabItemDetailGetDTOs) {
        this.cmInvestigationLabItemDetailGetDTOs = cmInvestigationLabItemDetailGetDTOs;
    }
}
