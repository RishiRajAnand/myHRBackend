package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch;
/*
* created by Brighty on 30-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionTrackerDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class IpPatientSearchServiceResponse extends CommonServiceResponse {

    private Page<IpAdmissionTrackerDTO> patientDTOs;

    //getters and setters

    public IpPatientSearchServiceResponse(Page<IpAdmissionTrackerDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }

    public IpPatientSearchServiceResponse() {
    }

    //constructor

    public Page<IpAdmissionTrackerDTO> getPatientDTOs() {
        return patientDTOs;
    }

    public void setPatientDTOs(Page<IpAdmissionTrackerDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }
}
