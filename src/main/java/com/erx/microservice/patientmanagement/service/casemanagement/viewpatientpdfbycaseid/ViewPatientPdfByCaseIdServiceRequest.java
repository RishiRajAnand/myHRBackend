package com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ViewPdfRequestDTO;

public class ViewPatientPdfByCaseIdServiceRequest implements CommonServiceRequest {

    private ViewPdfRequestDTO viewPdfRequestDTO;

    //constructor

    public ViewPatientPdfByCaseIdServiceRequest() {
    }

    public ViewPatientPdfByCaseIdServiceRequest(ViewPdfRequestDTO viewPdfRequestDTO) {
        this.viewPdfRequestDTO = viewPdfRequestDTO;
    }

    //getters and setters

    public ViewPdfRequestDTO getViewPdfRequestDTO() {
        return viewPdfRequestDTO;
    }

    public void setViewPdfRequestDTO(ViewPdfRequestDTO viewPdfRequestDTO) {
        this.viewPdfRequestDTO = viewPdfRequestDTO;
    }
}
