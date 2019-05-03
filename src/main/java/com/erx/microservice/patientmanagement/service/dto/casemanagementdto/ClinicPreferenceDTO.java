package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 06/10/18.
 */

public class ClinicPreferenceDTO {

    private long examOP;
    private long examIP;
    private long therapyEstimate;
    private boolean generateLab;

    public long getExamOP() {
        return examOP;
    }

    public void setExamOP(long examOP) {
        this.examOP = examOP;
    }

    public long getExamIP() {
        return examIP;
    }

    public void setExamIP(long examIP) {
        this.examIP = examIP;
    }

    public long getTherapyEstimate() {
        return therapyEstimate;
    }

    public void setTherapyEstimate(long therapyEstimate) {
        this.therapyEstimate = therapyEstimate;
    }

    public boolean isGenerateLab() {
        return generateLab;
    }

    public void setGenerateLab(boolean generateLab) {
        this.generateLab = generateLab;
    }
}
