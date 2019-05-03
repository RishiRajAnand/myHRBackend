package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterByClinicLocationDTO;

import java.util.List;

public class GetAllBedTypeMastersByIsDayCareServiceResponse extends CommonServiceResponse {
    private List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS;

    //getbedtypevisittypemaster constructor
    public GetAllBedTypeMastersByIsDayCareServiceResponse(List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS) {
        this.bedTypeMasterByClinicLocationDTOS = bedTypeMasterByClinicLocationDTOS;
    }

    public GetAllBedTypeMastersByIsDayCareServiceResponse() {
    }

    //getter and setters of getbedtypevisit type masters
    public List<BedTypeMasterByClinicLocationDTO> getBedTypeMasterByClinicLocationDTOS() {
        return bedTypeMasterByClinicLocationDTOS;
    }

    public void setBedTypeMasterByClinicLocationDTOS(List<BedTypeMasterByClinicLocationDTO> bedTypeMasterByClinicLocationDTOS) {
        this.bedTypeMasterByClinicLocationDTOS = bedTypeMasterByClinicLocationDTOS;
    }
}
