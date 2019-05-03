package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 20/08/18.
 */

public class CmObservationCategoryDataDTO {

    private Long id;
    private Long lookupValueId;
    private CmObservationCategoryDTO cmObservationCategoryDTO;
    private CmObservationDataDTO cmObservationDataDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmObservationCategoryDTO getCmObservationCategoryDTO() {
        return cmObservationCategoryDTO;
    }

    public void setCmObservationCategoryDTO(CmObservationCategoryDTO cmObservationCategoryDTO) {
        this.cmObservationCategoryDTO = cmObservationCategoryDTO;
    }

    public CmObservationDataDTO getCmObservationDataDTO() {
        return cmObservationDataDTO;
    }

    public void setCmObservationDataDTO(CmObservationDataDTO cmObservationDataDTO) {
        this.cmObservationDataDTO = cmObservationDataDTO;
    }

    public Long getLookupValueId() {
        return lookupValueId;
    }

    public void setLookupValueId(Long lookupValueId) {
        this.lookupValueId = lookupValueId;
    }
}
