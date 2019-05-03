package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;

/**
 * Created by Latha on 20/08/18.
 */

public class CmMasterDetailsDTO {

    private Long id;
    private LocalDateTime caseCreatedDate;
    private String doctorNote;
    private Long bmOrderId;
    private String bmOrderNumber;
    private Long patientAppointmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCaseCreatedDate() {
        return caseCreatedDate;
    }

    public void setCaseCreatedDate(LocalDateTime caseCreatedDate) {
        this.caseCreatedDate = caseCreatedDate;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public String getBmOrderNumber() {
        return bmOrderNumber;
    }

    public void setBmOrderNumber(String bmOrderNumber) {
        this.bmOrderNumber = bmOrderNumber;
    }
}
