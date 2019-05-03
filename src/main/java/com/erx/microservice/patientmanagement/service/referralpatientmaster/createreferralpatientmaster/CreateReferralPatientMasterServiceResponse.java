package com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster;



/*
* created by Raushan on 20-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.ReferralPatientMasterDTO;

public class CreateReferralPatientMasterServiceResponse extends CommonServiceResponse {

    private ReferralPatientMasterDTO rferralPatientMasterDTO;

    //Constrictor

    public CreateReferralPatientMasterServiceResponse() {
    }

    public CreateReferralPatientMasterServiceResponse(ReferralPatientMasterDTO rferralPatientMasterDTO) {
        this.rferralPatientMasterDTO = rferralPatientMasterDTO;
    }
    //getter and setter
    public ReferralPatientMasterDTO getRferralPatientMasterDTO() {
        return rferralPatientMasterDTO;
    }

    public void setRferralPatientMasterDTO(ReferralPatientMasterDTO rferralPatientMasterDTO) {
        this.rferralPatientMasterDTO = rferralPatientMasterDTO;
    }
}
