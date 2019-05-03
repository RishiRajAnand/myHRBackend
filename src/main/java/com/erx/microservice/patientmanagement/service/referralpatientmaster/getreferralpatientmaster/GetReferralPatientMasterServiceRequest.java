package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmaster;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterServiceRequest implements CommonServiceRequest {

    private long clinicLocationId;
   //Constructor
    public GetReferralPatientMasterServiceRequest(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
    //getter and setter
    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
