package com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveComplaintsDTO;

public class SaveComplaintServiceRequest implements CommonServiceRequest {

    private SaveComplaintsDTO saveComplaintsDTO;

    //constructor

    public SaveComplaintServiceRequest(SaveComplaintsDTO saveComplaintsDTO) {
        this.saveComplaintsDTO = saveComplaintsDTO;
    }

    public SaveComplaintServiceRequest() {
    }

    //getters and setters

    public SaveComplaintsDTO getSaveComplaintsDTO() {
        return saveComplaintsDTO;
    }

    public void setSaveComplaintsDTO(SaveComplaintsDTO saveComplaintsDTO) {
        this.saveComplaintsDTO = saveComplaintsDTO;
    }
}
