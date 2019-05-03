package com.erx.microservice.patientmanagement.web.rest;
/*
* created by raushan on 13-07-2018
* */

import com.erx.microservice.patientmanagement.service.dto.NotificationDTO;
import com.erx.microservice.patientmanagement.service.ipadmission.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.patientsearch.PatientSearchServiceResponse;
import com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification.SaveAppointmentTransferNotificationService;
import com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification.SaveAppointmentTransferNotificationServiceRequest;
import com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification.SaveAppointmentTransferNotificationServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private SaveAppointmentTransferNotificationService saveAppointmentTransferNotificationService;

    //To  save Appointment Transfer Notification
    @RequestMapping(value = "/saveAppointmentTransferNotification", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveAppointmentTransferNotification(@RequestBody NotificationDTO  notificationDTO) {
        log.debug("REST request to  save Appointment Transfer Notification");
        SaveAppointmentTransferNotificationServiceResponse response = new SaveAppointmentTransferNotificationServiceResponse();
        try {
            SaveAppointmentTransferNotificationServiceRequest request = new SaveAppointmentTransferNotificationServiceRequest(notificationDTO);
            response = saveAppointmentTransferNotificationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save Appointment Transfer Notification");
            log.error("Failed to save Appointment Transfer Notification");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

}
