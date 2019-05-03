package com.erx.microservice.patientmanagement.service;

/**
 * Created by Srilatha on 13/10/17.
 */
public abstract class CommonServiceResponse {

    public static boolean SUCCESSFUL = true;
    public String message;
    Integer errorCode;
    String errorMessage;


    public CommonServiceResponse() {

    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
