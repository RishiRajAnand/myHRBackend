package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Latha on 18/09/18.
 */

public class CmInvestigationLabItemDetailGetDTO {

    private String investigationNotes;
    private String testedOn;
    private long serviceCatalogueId;
    private String investigationItemName;
    private String investigationItemPath;
    private String investigationItemPathContentType;
    private List<String> investigationItemPaths;

    public String getInvestigationNotes() {
        return investigationNotes;
    }

    public void setInvestigationNotes(String investigationNotes) {
        this.investigationNotes = investigationNotes;
    }

    public long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public String getInvestigationItemName() {
        return investigationItemName;
    }

    public void setInvestigationItemName(String investigationItemName) {
        this.investigationItemName = investigationItemName;
    }

    public String getInvestigationItemPath() {
        return investigationItemPath;
    }

    public void setInvestigationItemPath(String investigationItemPath) {
        this.investigationItemPath = investigationItemPath;
    }

    public List<String> getInvestigationItemPaths() {
        return investigationItemPaths;
    }

    public void setInvestigationItemPaths(List<String> investigationItemPaths) {
        this.investigationItemPaths = investigationItemPaths;
    }

    public String getTestedOn() {
        return testedOn;
    }

    public void setTestedOn(String testedOn) {
        this.testedOn = testedOn;
    }

    public String getInvestigationItemPathContentType() {
        return investigationItemPathContentType;
    }

    public void setInvestigationItemPathContentType(String investigationItemPathContentType) {
        this.investigationItemPathContentType = investigationItemPathContentType;
    }
}
