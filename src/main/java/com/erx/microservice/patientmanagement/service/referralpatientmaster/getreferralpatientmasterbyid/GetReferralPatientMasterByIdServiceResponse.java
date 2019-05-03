package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid;



/*
* created by Raushan on 22-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.ReferralPatientMasterDTO;

public class GetReferralPatientMasterByIdServiceResponse extends CommonServiceResponse {

    private ReferralPatientMasterDTO referralPatientMasterDTO;
    private String doctorName;

    //constructor

    public GetReferralPatientMasterByIdServiceResponse() {
    }

    public GetReferralPatientMasterByIdServiceResponse(ReferralPatientMasterDTO referralPatientMasterDTO, String doctorName) {
        this.referralPatientMasterDTO = referralPatientMasterDTO;
        this.doctorName = doctorName;
    }
   //getter and setter
    public ReferralPatientMasterDTO getReferralPatientMasterDTO() {
        return referralPatientMasterDTO;
    }

    public void setReferralPatientMasterDTO(ReferralPatientMasterDTO referralPatientMasterDTO) {
        this.referralPatientMasterDTO = referralPatientMasterDTO;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
