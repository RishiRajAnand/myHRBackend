package com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment;

/*
* created by Latha on 29-11-2017.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetServicesByPatientAppointmentService {

    GetServicesByPatientAppointmentResponse execute(GetServicesByPatientAppointmentRequest request) throws ServiceException;
}
