package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;

import java.util.List;

public class GetAllBedTypeMastersByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<BedTypeMasterDTO> bedTypeMasterDTOs;

    public GetAllBedTypeMastersByClinicLocationServiceResponse() {
    }

    public GetAllBedTypeMastersByClinicLocationServiceResponse(List<BedTypeMasterDTO> bedTypeMasterDTOs) {
        this.bedTypeMasterDTOs = bedTypeMasterDTOs;
    }

    public List<BedTypeMasterDTO> getBedTypeMasterDTOs() {

        return bedTypeMasterDTOs;
    }

    public void setBedTypeMasterDTOs(List<BedTypeMasterDTO> bedTypeMasterDTOs) {
        this.bedTypeMasterDTOs = bedTypeMasterDTOs;
    }
}
