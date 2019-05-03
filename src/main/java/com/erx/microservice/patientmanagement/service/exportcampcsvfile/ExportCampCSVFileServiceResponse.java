/*
* created by Raushan on 15-02-2018
* */
package com.erx.microservice.patientmanagement.service.exportcampcsvfile;


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

import java.io.File;


public class ExportCampCSVFileServiceResponse extends CommonServiceResponse {
    private File file;

    //Constructor
    public ExportCampCSVFileServiceResponse() {
    }

    public ExportCampCSVFileServiceResponse(File file) {
        this.file = file;
    }

    //getter and setter
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}