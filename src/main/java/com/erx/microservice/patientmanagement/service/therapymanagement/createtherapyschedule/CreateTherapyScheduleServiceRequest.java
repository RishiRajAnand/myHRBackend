package com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule;



/*
* created by Raushan on 27-11-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;

public class CreateTherapyScheduleServiceRequest implements CommonServiceRequest {

    private Long caseId;
    private String startDate;
    private long clinicId;
    //Constructor
    public CreateTherapyScheduleServiceRequest(Long caseId, String startDate, long clinicId) {
        this.caseId = caseId;
        this.startDate = startDate;
        this.clinicId = clinicId;
    }

    public CreateTherapyScheduleServiceRequest() {
    }
    //getter and setter

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getClinicId() {
        return clinicId;
    }

    public void setClinicId(long clinicId) {
        this.clinicId = clinicId;
    }
}
