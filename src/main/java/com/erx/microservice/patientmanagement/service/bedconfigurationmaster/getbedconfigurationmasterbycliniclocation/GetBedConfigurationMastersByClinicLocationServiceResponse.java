package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterByIdDTO;

import java.util.List;

public class GetBedConfigurationMastersByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<BedConfigurationMasterByIdDTO> bedConfigurationMasterByIdDTOs;

    public GetBedConfigurationMastersByClinicLocationServiceResponse(List<BedConfigurationMasterByIdDTO> bedConfigurationMasterByIdDTOs) {
        this.bedConfigurationMasterByIdDTOs = bedConfigurationMasterByIdDTOs;
    }

    public GetBedConfigurationMastersByClinicLocationServiceResponse() {
    }

    public List<BedConfigurationMasterByIdDTO> getBedConfigurationMasterByIdDTOs() {

        return bedConfigurationMasterByIdDTOs;
    }

    public void setBedConfigurationMasterByIdDTOs(List<BedConfigurationMasterByIdDTO> bedConfigurationMasterByIdDTOs) {
        this.bedConfigurationMasterByIdDTOs = bedConfigurationMasterByIdDTOs;
    }
}
