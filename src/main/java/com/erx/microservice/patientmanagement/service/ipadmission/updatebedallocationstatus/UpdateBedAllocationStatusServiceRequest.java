package com.erx.microservice.patientmanagement.service.ipadmission.updatebedallocationstatus;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class UpdateBedAllocationStatusServiceRequest implements CommonServiceRequest {

    private Long patientId;
    private String bedMasterAllocationStatus;

    public UpdateBedAllocationStatusServiceRequest(Long patientId, String bedMasterAllocationStatus) {
        this.patientId = patientId;
        this.bedMasterAllocationStatus = bedMasterAllocationStatus;
    }

    public UpdateBedAllocationStatusServiceRequest() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getBedMasterAllocationStatus() {
        return bedMasterAllocationStatus;
    }

    public void setBedMasterAllocationStatus(String bedMasterAllocationStatus) {
        this.bedMasterAllocationStatus = bedMasterAllocationStatus;
    }
}
