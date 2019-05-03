package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface IpAdmissionMapper {

    IpAdmissionDTO ipAdmissionToIpAdmissionDTO(IpAdmission ipAdmission);

    IpAdmission ipAdmissionDTOToIpAdmission(IpAdmissionDTO ipAdmissionDTO);

    List<IpAdmissionDTO> ipAdmissionsToIpAdmissionDTOs(List<IpAdmission> ipAdmissions);

    List<IpAdmission> ipAdmissionDTOsToIpAdmissions(List<IpAdmissionDTO> ipAdmissionDTOs);
}
