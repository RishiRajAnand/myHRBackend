package com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype;

/*
* created by raushan on 30-05-2018.
* */

import org.hibernate.service.spi.ServiceException;

public interface GetPatientAppointmentByIdAndVisitTypeService {

    GetPatientAppointmentByIdAndVisitTypeServiceResponse execute(GetPatientAppointmentByIdAndVisitTypeServiceRequest request) throws ServiceException;
}
