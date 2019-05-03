package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralid;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByReferralIdServiceRequest implements CommonServiceRequest {

    private String referralId;
    private long clinicLocationId;
   //Constructor
    public GetReferralPatientMasterByReferralIdServiceRequest(long clinicLocationId,String referralId) {
        this.referralId = referralId;
        this.clinicLocationId=clinicLocationId;
    }
   //getter and setter
    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
