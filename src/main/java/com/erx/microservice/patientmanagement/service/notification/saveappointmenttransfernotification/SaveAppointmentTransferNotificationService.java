package com.erx.microservice.patientmanagement.service.notification.saveappointmenttransfernotification;

/*
* created by raushan on 13-07-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface SaveAppointmentTransferNotificationService {

    SaveAppointmentTransferNotificationServiceResponse execute(SaveAppointmentTransferNotificationServiceRequest request) throws ServiceException;
}
