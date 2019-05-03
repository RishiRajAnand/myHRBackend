package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByReferralNameServiceRequest implements CommonServiceRequest {

    private String referralName;
    private long clinicLocationId;
    //Constructor
    public GetReferralPatientMasterByReferralNameServiceRequest(long clinicLocationId, String referralName) {
        this.clinicLocationId=clinicLocationId;
        this.referralName = referralName;
    }
    //getter and setter
    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
