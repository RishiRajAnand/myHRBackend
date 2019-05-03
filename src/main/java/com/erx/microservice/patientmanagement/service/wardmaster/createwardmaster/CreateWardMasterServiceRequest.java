package com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterDTO;

public class CreateWardMasterServiceRequest implements CommonServiceRequest {

    private WardMasterDTO wardMasterDTO;

    private Long clinicId;

    //Getters and setters

    public CreateWardMasterServiceRequest(WardMasterDTO wardMasterDTO, Long clinicId) {
        this.wardMasterDTO = wardMasterDTO;
        this.clinicId = clinicId;
    }

    //Getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public WardMasterDTO getWardMasterDTO() {
        return wardMasterDTO;
    }

    public void setWardMasterDTO(WardMasterDTO wardMasterDTO) {
        this.wardMasterDTO = wardMasterDTO;
    }
}
