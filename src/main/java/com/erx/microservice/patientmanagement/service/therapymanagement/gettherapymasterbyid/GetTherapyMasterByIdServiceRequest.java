package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid;

/*
* created by Latha on 08-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class GetTherapyMasterByIdServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long therapyMasterId;

    //constructor

    public GetTherapyMasterByIdServiceRequest() {
    }

    public GetTherapyMasterByIdServiceRequest(Long clinicId, Long therapyMasterId) {
        this.clinicId = clinicId;
        this.therapyMasterId = therapyMasterId;
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }
}
