package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferraltype;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByReferralTypeServiceRequest implements CommonServiceRequest {

    private String referralType;
    private long clinicLocationId;
   //Constructor
    public GetReferralPatientMasterByReferralTypeServiceRequest(long clinicLocationId, String referralType) {
        this.clinicLocationId=clinicLocationId;
        this.referralType = referralType;
    }
   //getter and setter
    public String getReferralType() {
        return referralType;
    }

    public void setReferralType(String referralType) {
        this.referralType = referralType;
    }

    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
