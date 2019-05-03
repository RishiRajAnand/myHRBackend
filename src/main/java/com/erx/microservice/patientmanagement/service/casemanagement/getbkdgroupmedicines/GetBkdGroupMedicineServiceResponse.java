package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BkdGroupMedicineDTO;
import org.springframework.data.domain.Page;

public class GetBkdGroupMedicineServiceResponse extends CommonServiceResponse {

    private Page<BkdGroupMedicineDTO> bkdGroupMedicineDTOs;

    //constructor
    public GetBkdGroupMedicineServiceResponse(Page<BkdGroupMedicineDTO> bkdGroupMedicineDTOs) {
        this.bkdGroupMedicineDTOs = bkdGroupMedicineDTOs;
    }

    public GetBkdGroupMedicineServiceResponse() {
    }

    //getters and setters

    public Page<BkdGroupMedicineDTO> getBkdGroupMedicineDTOs() {
        return bkdGroupMedicineDTOs;
    }

    public void setBkdGroupMedicineDTOs(Page<BkdGroupMedicineDTO> bkdGroupMedicineDTOs) {
        this.bkdGroupMedicineDTOs = bkdGroupMedicineDTOs;
    }
}
