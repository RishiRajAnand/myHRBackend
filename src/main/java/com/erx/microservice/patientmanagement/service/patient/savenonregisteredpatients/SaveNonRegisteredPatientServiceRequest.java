package com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients;
/*
 * created by Manisha on 16-07-2018
 * */
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.NonRegisteredPatientDTO;

public class SaveNonRegisteredPatientServiceRequest implements CommonServiceRequest {
    private NonRegisteredPatientDTO nonRegisteredPatientDTO;

    public SaveNonRegisteredPatientServiceRequest() {
    }

    public SaveNonRegisteredPatientServiceRequest(NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        this.nonRegisteredPatientDTO = nonRegisteredPatientDTO;
    }

    public NonRegisteredPatientDTO getNonRegisteredPatientDTO() {
        return nonRegisteredPatientDTO;
    }

    public void setNonRegisteredPatientDTO(NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        this.nonRegisteredPatientDTO = nonRegisteredPatientDTO;
    }
}
