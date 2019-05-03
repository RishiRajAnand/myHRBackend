package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid;

/*
* created by Raushan on 29-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyScheduleDetailByTherapyIdServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private Long therapyMasterId;
    private  Long patientId;

    //constructor

    public GetTherapyScheduleDetailByTherapyIdServiceRequest() {
    }

    public GetTherapyScheduleDetailByTherapyIdServiceRequest(Long clinicId, Long therapyMasterId, Long patientId) {
        this.clinicId = clinicId;
        this.therapyMasterId = therapyMasterId;
        this.patientId = patientId;
    }


//getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
