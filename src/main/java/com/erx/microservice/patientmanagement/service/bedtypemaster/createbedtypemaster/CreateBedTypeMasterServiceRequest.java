package com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;


/*
* created by Brighty on 17-11-2017
* */

public class CreateBedTypeMasterServiceRequest implements CommonServiceRequest {

    private BedTypeMasterDTO bedTypeMasterDTO;

    private Long clinicId;

    //Constructor

    public CreateBedTypeMasterServiceRequest(BedTypeMasterDTO bedTypeMasterDTO, Long clinicId) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
        this.clinicId = clinicId;
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public BedTypeMasterDTO getBedTypeMasterDTO() {

        return bedTypeMasterDTO;
    }

    public void setBedTypeMasterDTO(BedTypeMasterDTO bedTypeMasterDTO) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
    }
}
