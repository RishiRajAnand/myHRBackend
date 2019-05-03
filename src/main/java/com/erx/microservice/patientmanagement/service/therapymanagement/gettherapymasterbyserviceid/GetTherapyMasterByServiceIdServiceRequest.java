package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyMasterByServiceIdServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long serviceCatalogueId;

    //constructor

    public GetTherapyMasterByServiceIdServiceRequest() {
    }

    public GetTherapyMasterByServiceIdServiceRequest(Long clinicId, Long serviceCatalogueId) {
        this.clinicId = clinicId;
        this.serviceCatalogueId = serviceCatalogueId;
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }
}
