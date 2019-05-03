package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbydoctorname;



/*
* created by Raushan on 21-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetReferralPatientMasterByDoctorNameServiceRequest implements CommonServiceRequest {

    private String doctorName;
    private long clinicLocationId;
   //Constructor
    public GetReferralPatientMasterByDoctorNameServiceRequest(long clinicLocationId, String doctorName) {
        this.doctorName = doctorName;
        this.clinicLocationId=clinicLocationId;
    }
    //getter and setter
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
