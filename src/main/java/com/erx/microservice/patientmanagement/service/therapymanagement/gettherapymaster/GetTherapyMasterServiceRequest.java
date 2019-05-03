package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class GetTherapyMasterServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Pageable pageable;

    //constructor

    public GetTherapyMasterServiceRequest(Long clinicId, Pageable pageable) {
        this.clinicId = clinicId;
        this.pageable = pageable;
    }

    public GetTherapyMasterServiceRequest() {
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
