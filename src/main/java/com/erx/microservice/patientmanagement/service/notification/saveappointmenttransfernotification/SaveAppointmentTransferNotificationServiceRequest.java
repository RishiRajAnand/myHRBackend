package com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification;

/*
* created by raushan on 13-07-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.NotificationDTO;

public class SaveAppointmentTransferNotificationServiceRequest implements CommonServiceRequest {

    private NotificationDTO notificationDTO;

    //constructor
    public SaveAppointmentTransferNotificationServiceRequest(NotificationDTO notificationDTO) {
        this.notificationDTO = notificationDTO;
    }
    //getters and setters

    public NotificationDTO getNotificationDTO() {
        return notificationDTO;
    }

    public void setNotificationDTO(NotificationDTO notificationDTO) {
        this.notificationDTO = notificationDTO;
    }


}
