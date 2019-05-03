package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;

public class GetBedTypeMasterByIdServiceResponse extends CommonServiceResponse {

    private BedTypeMasterDTO bedTypeMasterDTO;

    public GetBedTypeMasterByIdServiceResponse() {
    }

    public GetBedTypeMasterByIdServiceResponse(BedTypeMasterDTO bedTypeMasterDTO) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
    }

    public BedTypeMasterDTO getBedTypeMasterDTO() {

        return bedTypeMasterDTO;
    }

    public void setBedTypeMasterDTO(BedTypeMasterDTO bedTypeMasterDTO) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
    }
}
