package com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine;

/*
* created by Latha on 02-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteBkdGroupMedicineServiceRequest implements CommonServiceRequest {

    private Long cmGroupMedicineMasterId;

    //constructor

    public DeleteBkdGroupMedicineServiceRequest(Long cmGroupMedicineMasterId) {
        this.cmGroupMedicineMasterId = cmGroupMedicineMasterId;
    }

    public DeleteBkdGroupMedicineServiceRequest() {
    }

    //getters and setters

    public Long getCmGroupMedicineMasterId() {
        return cmGroupMedicineMasterId;
    }

    public void setCmGroupMedicineMasterId(Long cmGroupMedicineMasterId) {
        this.cmGroupMedicineMasterId = cmGroupMedicineMasterId;
    }
}
