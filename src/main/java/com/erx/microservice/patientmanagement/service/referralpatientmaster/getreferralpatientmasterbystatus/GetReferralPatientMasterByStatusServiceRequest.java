package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbystatus;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByStatusServiceRequest implements CommonServiceRequest {

    private String status;
    private long clinicLocationId;
  //Constructor
    public GetReferralPatientMasterByStatusServiceRequest(long clinicLocationId, String status) {
        this.clinicLocationId=clinicLocationId;
        this.status = status;
    }
   //getter and setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
