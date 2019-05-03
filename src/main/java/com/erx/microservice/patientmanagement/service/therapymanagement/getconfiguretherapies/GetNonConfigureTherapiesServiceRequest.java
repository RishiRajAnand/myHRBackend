package com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies;

/*
* created by Latha on 27-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;


public class GetNonConfigureTherapiesServiceRequest implements CommonServiceRequest {

    private Long moduleSectionMasterId;
    private Long clinicId;
    private Long clinicLocationId;

    //constructors

    public GetNonConfigureTherapiesServiceRequest() {
    }

    public GetNonConfigureTherapiesServiceRequest(Long moduleSectionMasterId, Long clinicId, Long clinicLocationId) {
        this.moduleSectionMasterId = moduleSectionMasterId;
        this.clinicId = clinicId;
        this.clinicLocationId = clinicLocationId;
    }

    //getters and setters

    public Long getModuleSectionMasterId() {
        return moduleSectionMasterId;
    }

    public void setModuleSectionMasterId(Long moduleSectionMasterId) {
        this.moduleSectionMasterId = moduleSectionMasterId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
