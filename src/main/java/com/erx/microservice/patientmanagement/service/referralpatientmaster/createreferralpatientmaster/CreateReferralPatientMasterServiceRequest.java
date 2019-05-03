package com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster;



/*
* created by Raushan on 20-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.CreateReferralPatientMasterDTO;

public class CreateReferralPatientMasterServiceRequest implements CommonServiceRequest {

    private CreateReferralPatientMasterDTO createReferralPatientMasterDTO;

   //Constructor

    public CreateReferralPatientMasterServiceRequest(CreateReferralPatientMasterDTO createReferralPatientMasterDTO) {
        this.createReferralPatientMasterDTO = createReferralPatientMasterDTO;
    }

    //getter and setter

    public CreateReferralPatientMasterDTO getCreateReferralPatientMasterDTO() {
        return createReferralPatientMasterDTO;
    }

    public void setCreateReferralPatientMasterDTO(CreateReferralPatientMasterDTO createReferralPatientMasterDTO) {
        this.createReferralPatientMasterDTO = createReferralPatientMasterDTO;
    }
}
