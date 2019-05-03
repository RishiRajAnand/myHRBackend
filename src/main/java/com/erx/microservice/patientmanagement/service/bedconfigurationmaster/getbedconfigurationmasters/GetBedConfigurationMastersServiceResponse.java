package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters;

/*
* created by Brighty on 20-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterByClinicLocationDTO;

import java.util.List;

public class GetBedConfigurationMastersServiceResponse extends CommonServiceResponse {

    private List<BedConfigurationMasterByClinicLocationDTO> bedConfigurationMasterByClinicLocationDTOS;

    public GetBedConfigurationMastersServiceResponse(List<BedConfigurationMasterByClinicLocationDTO> bedConfigurationMasterByClinicLocationDTOS) {
        this.bedConfigurationMasterByClinicLocationDTOS = bedConfigurationMasterByClinicLocationDTOS;
    }

    public GetBedConfigurationMastersServiceResponse() {
    }

    public List<BedConfigurationMasterByClinicLocationDTO> getBedConfigurationMasterByClinicLocationDTOS() {

        return bedConfigurationMasterByClinicLocationDTOS;
    }

    public void setBedConfigurationMasterByClinicLocationDTOS(List<BedConfigurationMasterByClinicLocationDTO> bedConfigurationMasterByClinicLocationDTOS) {
        this.bedConfigurationMasterByClinicLocationDTOS = bedConfigurationMasterByClinicLocationDTOS;
    }
}
