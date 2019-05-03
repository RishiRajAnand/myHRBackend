package com.erx.microservice.patientmanagement.service.importcsvfile;

/*
* created by Brighty on 26-03-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

import java.util.List;

public class ImportCampCSVFileServiceResponse extends CommonServiceResponse {

    private List<String> logs;

    //constructor

    public ImportCampCSVFileServiceResponse(List<String> logs) {
        this.logs = logs;
    }

    public ImportCampCSVFileServiceResponse() {
    }

    //getters and setters

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}