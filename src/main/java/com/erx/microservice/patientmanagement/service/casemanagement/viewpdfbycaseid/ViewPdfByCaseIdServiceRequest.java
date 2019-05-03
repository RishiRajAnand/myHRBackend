package com.erx.microservice.patientmanagement.service.casemanagement.viewpdfbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ViewPdfRequestDTO;

public class ViewPdfByCaseIdServiceRequest implements CommonServiceRequest {

    private ViewPdfRequestDTO viewPdfRequestDTO;

    //constructor

    public ViewPdfByCaseIdServiceRequest() {
    }

    public ViewPdfByCaseIdServiceRequest(ViewPdfRequestDTO viewPdfRequestDTO) {
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
