package com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;

/*
 * created by Brighty on 17-11-2017
 * */

public class CreateBedTypeMasterServiceResponse extends CommonServiceResponse {

    private BedTypeMasterDTO bedTypeMasterDTO;

    public CreateBedTypeMasterServiceResponse(BedTypeMasterDTO bedTypeMasterDTO) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
    }

    public CreateBedTypeMasterServiceResponse() {

    }

    public BedTypeMasterDTO getBedTypeMasterDTO() {
        return bedTypeMasterDTO;
    }

    public void setBedTypeMasterDTO(BedTypeMasterDTO bedTypeMasterDTO) {
        this.bedTypeMasterDTO = bedTypeMasterDTO;
    }

}