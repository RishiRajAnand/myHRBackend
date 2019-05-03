/*
* created by Raushan on 15-02-2018
* */
package com.erx.microservice.patientmanagement.service.exportcampcsvfile;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

import javax.servlet.http.HttpServletResponse;

public class ExportCampCSVFileServiceRequest implements CommonServiceRequest {

    private Long clinicId;
    private HttpServletResponse httpServletResponse;
    //Constructor

    public ExportCampCSVFileServiceRequest() {
    }

    public ExportCampCSVFileServiceRequest(Long clinicId, HttpServletResponse httpServletResponse) {
        this.clinicId = clinicId;
        this.httpServletResponse = httpServletResponse;
    }

    //getter and setter
    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}