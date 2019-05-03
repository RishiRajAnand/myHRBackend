package com.erx.microservice.patientmanagement.service.patientalldetails;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;

/*
 * created by Bahubali on 17-10-2018
 * */
public class SavePatientAllDetailsServiceRequest implements CommonServiceRequest {

    private PatientAllDetailsDTO patientAllDetailsDTO;

    public SavePatientAllDetailsServiceRequest(PatientAllDetailsDTO patientAllDetailsDTO) {
        this.patientAllDetailsDTO = patientAllDetailsDTO;
    }

    public PatientAllDetailsDTO getPatientAllDetailsDTO() {
        return patientAllDetailsDTO;
    }

    public void setPatientAllDetailsDTO(PatientAllDetailsDTO patientAllDetailsDTO) {
        this.patientAllDetailsDTO = patientAllDetailsDTO;
    }
}
