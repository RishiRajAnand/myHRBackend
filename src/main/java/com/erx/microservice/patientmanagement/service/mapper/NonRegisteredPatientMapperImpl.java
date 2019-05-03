package com.erx.microservice.patientmanagement.service.mapper;
/*
 * created by Manisha on 16-07-2018
 * */
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.service.dto.NonRegisteredPatientDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {})
public class NonRegisteredPatientMapperImpl implements NonRegisteredPatientMapper {
    @Autowired
    private LookupValueRepository lookupValueRepository;

    @Override
    public Patient nonRegisteredPatientDTOTOPatient(NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        Patient patient = new Patient();
        patient.setPatientName(nonRegisteredPatientDTO.getName());
        patient.setGender(nonRegisteredPatientDTO.getGender());
        patient.setDateOfBirth(nonRegisteredPatientDTO.getDateOfBirth());
        patient.setMobileNumber(nonRegisteredPatientDTO.getMobileNumber());
        patient.setEmail(nonRegisteredPatientDTO.getEmail());
        patient.setRegistered(nonRegisteredPatientDTO.isRegistered());
        //setting salutation
        LookupValue lookupValue = lookupValueRepository.findLookUpByName(nonRegisteredPatientDTO.getSalutation());
        if (lookupValue != null) {
            patient.setPatientSalutation(lookupValue);
        }
        //setting clinic id
        Clinic clinic = new Clinic();
        clinic.setId(nonRegisteredPatientDTO.getClinicId());
        patient.setClinic(clinic);
        //setting clinic location id
        ClinicLocation clinicLocation = new ClinicLocation();
        clinicLocation.setId(nonRegisteredPatientDTO.getClinicLocationId());
        patient.setClinicLocation(clinicLocation);
        return patient;
    }

    @Override
    public NonRegisteredPatientDTO patientToNonRegisteredPatientDTO(Patient patient) {
        NonRegisteredPatientDTO NonRegisteredPatientDTO = new NonRegisteredPatientDTO();
        NonRegisteredPatientDTO.setPatientId(patient.getId());
        return NonRegisteredPatientDTO;
    }
}
