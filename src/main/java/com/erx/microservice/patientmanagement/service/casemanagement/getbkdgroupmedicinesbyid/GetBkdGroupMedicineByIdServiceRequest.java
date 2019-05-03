package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.data.domain.Pageable;

public class GetBkdGroupMedicineByIdServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long groupMedicineId;

    //constructor
    public GetBkdGroupMedicineByIdServiceRequest() {
    }

    public GetBkdGroupMedicineByIdServiceRequest(Long clinicId, Long groupMedicineId) {
        this.clinicId = clinicId;
        this.groupMedicineId = groupMedicineId;
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getGroupMedicineId() {
        return groupMedicineId;
    }

    public void setGroupMedicineId(Long groupMedicineId) {
        this.groupMedicineId = groupMedicineId;
    }
}
