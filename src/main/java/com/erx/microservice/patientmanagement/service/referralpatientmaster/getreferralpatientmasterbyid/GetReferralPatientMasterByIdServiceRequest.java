package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid;



/*
* created by Raushan on 22-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByIdServiceRequest implements CommonServiceRequest {

    private Long referralId;

    //Constructor
    public GetReferralPatientMasterByIdServiceRequest( Long referralId) {
        this.referralId = referralId;
    }
   //getter and setter
    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }


}
