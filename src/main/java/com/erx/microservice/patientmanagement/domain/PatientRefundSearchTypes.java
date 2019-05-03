package com.erx.microservice.patientmanagement.domain;

public enum PatientRefundSearchTypes {
    MRN("mrn"), MOBILE("mobile"), NAME("name"), ALL("all");
    private String patientRefundSearchTypes;

    PatientRefundSearchTypes(String patientRefundSearchTypes) {
        this.patientRefundSearchTypes = patientRefundSearchTypes;
    }

    public String getPatientRefundSearchTypes() {
        return patientRefundSearchTypes;
    }

    public void setPatientRefundSearchTypes(String patientRefundSearchTypes) {
        this.patientRefundSearchTypes = patientRefundSearchTypes;
    }
}
