package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 18-09-2018
* */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "upload_report_type")
public class UploadReportType extends BaseModel{

    @Column(name="report_type_name", nullable=false)
    private String reportTypeName;

    @Column(name="description", nullable=true)
    private String description;

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
