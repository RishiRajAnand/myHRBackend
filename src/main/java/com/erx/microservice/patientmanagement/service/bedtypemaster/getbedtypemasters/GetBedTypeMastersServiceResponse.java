package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters;

/*
* created by Brighty on 16-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterByClinicLocationDTO;

import java.util.List;

public class GetBedTypeMastersServiceResponse extends CommonServiceResponse {

    private List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS;

    public GetBedTypeMastersServiceResponse(List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS) {
        this.bedTypeMasterByClinicLocationDTOS = bedTypeMasterByClinicLocationDTOS;
    }

    public GetBedTypeMastersServiceResponse() {
    }

    public List<BedTypeMasterByClinicLocationDTO> getBedTypeMasterByClinicLocationDTOS() {

        return bedTypeMasterByClinicLocationDTOS;
    }

    public void setBedTypeMasterByClinicLocationDTOS(List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS) {
        this.bedTypeMasterByClinicLocationDTOS = bedTypeMasterByClinicLocationDTOS;
    }
}
