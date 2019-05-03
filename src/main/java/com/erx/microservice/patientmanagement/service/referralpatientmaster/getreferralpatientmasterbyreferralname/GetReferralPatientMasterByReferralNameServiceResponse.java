package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname;



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.LeadTracketReferralPatientMasterDTO;

import java.util.List;

/*
* created by Raushan on 21-11-2017
* */

public class GetReferralPatientMasterByReferralNameServiceResponse extends CommonServiceResponse {

    private List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs;
    //Constructor

    public GetReferralPatientMasterByReferralNameServiceResponse() {
    }

    public GetReferralPatientMasterByReferralNameServiceResponse(List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs) {
        this.leadTracketReferralPatientMasterDTOs = leadTracketReferralPatientMasterDTOs;
    }
    //getter and setter
    public List<LeadTracketReferralPatientMasterDTO> getLeadTracketReferralPatientMasterDTOs() {
        return leadTracketReferralPatientMasterDTOs;
    }

    public void setLeadTracketReferralPatientMasterDTOs(List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs) {
        this.leadTracketReferralPatientMasterDTOs = leadTracketReferralPatientMasterDTOs;
    }
}
