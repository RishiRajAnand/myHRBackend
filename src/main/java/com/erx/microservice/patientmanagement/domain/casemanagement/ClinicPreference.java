package com.erx.microservice.patientmanagement.domain.casemanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;

import javax.persistence.*;

@Entity
@Table(name = "clinic_preference")
public class ClinicPreference extends BaseModel {

    @OneToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Column(name = "payment_policy", nullable = false)
    private String paymentPolicy;

    @Column(name = "common_holiday")
    private String commonHoliday;

    @Column(name = "mrd_format", nullable = false)
    private String mrd_format;

    @Column(name = "last_mrd", nullable = false)
    private String last_mrd;

    @Column(name = "is_exam_op")
    private long examOP;

    @Column(name = "is_exam_ip")
    private long examIP;

    @Column(name = "is_therapy_estimate")
    private long therapyEstimate;

    @Column(name = "is_generated_lab", nullable = false)
    private boolean generateLab;


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public void setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }

    public String getCommonHoliday() {
        return commonHoliday;
    }

    public void setCommonHoliday(String commonHoliday) {
        this.commonHoliday = commonHoliday;
    }

    public String getMrd_format() {
        return mrd_format;
    }

    public void setMrd_format(String mrd_format) {
        this.mrd_format = mrd_format;
    }

    public String getLast_mrd() {
        return last_mrd;
    }

    public void setLast_mrd(String last_mrd) {
        this.last_mrd = last_mrd;
    }

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
