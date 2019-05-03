package com.erx.microservice.patientmanagement.domain;

public enum ErxStatus {


    DUPLICATE_ERROR(66, "Duplication Name present");

    private final Integer value;
    private final String statusMessage;

    ErxStatus(Integer value, String errorMessage) {
        this.value = value;
        this.statusMessage = errorMessage;
    }

    public Integer getValue() {
        return value;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

}
