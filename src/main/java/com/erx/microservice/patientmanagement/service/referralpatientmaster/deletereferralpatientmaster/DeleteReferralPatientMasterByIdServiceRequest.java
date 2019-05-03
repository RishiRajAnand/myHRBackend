package com.erx.microservice.patientmanagement.service.referralpatientmaster.deletereferralpatientmaster;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteReferralPatientMasterByIdServiceRequest implements CommonServiceRequest {

    private long referralId;
   //Constructor

    public DeleteReferralPatientMasterByIdServiceRequest(long referralId) {
        this.referralId = referralId;
    }
    //getter and setter
    public long getReferralId() {
        return referralId;
    }

    public void setReferralId(long referralId) {
        this.referralId = referralId;
    }

}
