package com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification;

/*
 * created by raushan on 13-07-2018
 * */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;


public class SaveAppointmentTransferNotificationServiceResponse extends CommonServiceResponse {
    private String doctorName;

    //setter and getter
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
