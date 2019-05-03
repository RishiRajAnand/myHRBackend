package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster;

/*
* created by Brighty on 16-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterDTO;


public class CreateBedConfigurationMasterServiceRequest implements CommonServiceRequest {

    private BedConfigurationMasterDTO bedConfigurationMasterDTO;

    private Long clinicId;

    //Constructor

    public CreateBedConfigurationMasterServiceRequest(BedConfigurationMasterDTO bedConfigurationMasterDTO,
                                                      Long clinicId) {
        this.bedConfigurationMasterDTO = bedConfigurationMasterDTO;
        this.clinicId = clinicId;
    }

    //Getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public BedConfigurationMasterDTO getBedConfigurationMasterDTO() {

        return bedConfigurationMasterDTO;
    }

    public void setBedConfigurationMasterDTO(BedConfigurationMasterDTO bedConfigurationMasterDTO) {
        this.bedConfigurationMasterDTO = bedConfigurationMasterDTO;
    }
}
