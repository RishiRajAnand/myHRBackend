package com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid;

/*
* created by Raushan on 06-10-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionTrackerDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientCaseDTO;

import java.util.List;

public class GetAllocatedBedDetailsByIpAdmissionIdServiceResponse extends CommonServiceResponse {

    private IpAdmissionTrackerDTO patientDTO;

    //constructor


    public GetAllocatedBedDetailsByIpAdmissionIdServiceResponse(IpAdmissionTrackerDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public GetAllocatedBedDetailsByIpAdmissionIdServiceResponse() {

    }

    //getters and setters

    public IpAdmissionTrackerDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(IpAdmissionTrackerDTO patientDTO) {
        this.patientDTO = patientDTO;
    }
}
