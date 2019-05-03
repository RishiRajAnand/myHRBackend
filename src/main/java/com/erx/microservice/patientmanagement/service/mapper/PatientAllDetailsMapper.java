package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.PatientAllDetails;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PatientAllDetailsMapper {

    PatientAllDetails patientAllDetailsDTOToPatientAllDetails(PatientAllDetailsDTO patientAllDetailsDTO);
}

