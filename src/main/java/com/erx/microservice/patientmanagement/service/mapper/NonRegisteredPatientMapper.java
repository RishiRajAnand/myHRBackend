package com.erx.microservice.patientmanagement.service.mapper;
/*
 * created by Manisha on 16-07-2018
 * */
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.service.dto.NonRegisteredPatientDTO;

public interface NonRegisteredPatientMapper {
    Patient nonRegisteredPatientDTOTOPatient(NonRegisteredPatientDTO nonRegisteredPatientDTO);

    NonRegisteredPatientDTO patientToNonRegisteredPatientDTO(Patient patient);
}
