package com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor;

/*
* created by Brighty on 02-07-2018
* */

import com.erx.microservice.patientmanagement.exception.ServiceException;

public interface GetLastAppointmentByPatientAndDoctorService {

    GetLastAppointmentByPatientAndDoctorServiceResponse execute
            (GetLastAppointmentByPatientAndDoctorServiceRequest request)throws ServiceException;
}
