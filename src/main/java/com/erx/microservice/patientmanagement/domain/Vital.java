package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;

/**
 * Created by Latha on 17/08/2018.
 */

@Entity
@Table(name = "vital")
public class Vital extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable=true)
    private Patient patient;

    @Column(name = "height", nullable = true)
    private String height;

    @Column(name = "weight", nullable = true)
    private String weight;

    @Column(name = "head", nullable = true)
    private String head;

    @Column(name = "bp", nullable = true)
    private String bp;

    @Column(name = "temperature", nullable = true)
    private String temperature;

    @Column(name = "bmi", nullable = true)
    private String bmi;

    @Column(name = "pulse", nullable = true)
    private String pulse;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
}
