package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class GetBkdGroupMedicineServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Pageable pageable;

    //constructor

    public GetBkdGroupMedicineServiceRequest(Long clinicId, Pageable pageable) {
        this.clinicId = clinicId;
        this.pageable = pageable;
    }

    public GetBkdGroupMedicineServiceRequest() {
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
