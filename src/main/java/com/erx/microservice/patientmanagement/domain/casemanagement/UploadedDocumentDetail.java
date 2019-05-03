package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.City;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.UploadReportType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_document_detail")
public class UploadedDocumentDetail extends BaseModel{

    @Column(name = "uploaded_by", nullable=false)
    private Long uploadedBy;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_master_id", nullable=true)
    @JsonIgnore
    private CmMaster cmMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_investigation_id", nullable=true)
    @JsonIgnore
    private CmInvestigation cmInvestigation;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_investigation_detail_id", nullable=true)
    @JsonIgnore
    private CmInvestigationDetail cmInvestigationDetail;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "patient_id", nullable=true)
    @JsonIgnore
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "city_id", nullable=true)
    @JsonIgnore
    private City city;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "upload_report_type_id", nullable=true)
    @JsonIgnore
    private UploadReportType uploadReportType;

    @Column(name="investigation_item_name", nullable=true)
    private String investigationItem;

    @Column(name = "uploaded_on", nullable = false)
    private LocalDateTime uploadedOn;

    @Column(name="file_path", nullable=false)
    private String filePath;

    @Column(name="file_content_type", nullable=true)
    private String fileContentType;

    @Column(name="notes", nullable=true)
    private String notes;

    @Column(name="doctor_name", nullable=true)
    private String doctorName;

    @Column(name="source", nullable=true)
    private String source;

    @Column(name="report_given_date", nullable=true)
    private LocalDateTime reportGivenDate;

    public Long getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Long uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public CmInvestigation getCmInvestigation() {
        return cmInvestigation;
    }

    public void setCmInvestigation(CmInvestigation cmInvestigation) {
        this.cmInvestigation = cmInvestigation;
    }

    public CmInvestigationDetail getCmInvestigationDetail() {
        return cmInvestigationDetail;
    }

    public void setCmInvestigationDetail(CmInvestigationDetail cmInvestigationDetail) {
        this.cmInvestigationDetail = cmInvestigationDetail;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UploadReportType getUploadReportType() {
        return uploadReportType;
    }

    public void setUploadReportType(UploadReportType uploadReportType) {
        this.uploadReportType = uploadReportType;
    }

    public String getInvestigationItem() {
        return investigationItem;
    }

    public void setInvestigationItem(String investigationItem) {
        this.investigationItem = investigationItem;
    }

    public LocalDateTime getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(LocalDateTime uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getReportGivenDate() {
        return reportGivenDate;
    }

    public void setReportGivenDate(LocalDateTime reportGivenDate) {
        this.reportGivenDate = reportGivenDate;
    }
}
