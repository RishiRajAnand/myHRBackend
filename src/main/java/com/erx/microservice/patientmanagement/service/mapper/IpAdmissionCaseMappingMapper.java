package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionCaseMapping;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionCaseMappingDTO;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface IpAdmissionCaseMappingMapper {

    IpAdmissionCaseMappingDTO ipAdmissionCaseMappingToIpAdmissionCaseMappingDTO(IpAdmissionCaseMapping ipAdmissionCaseMapping);

    IpAdmissionCaseMapping ipAdmissionCaseMappingDTOToIpAdmissionCaseMapping(IpAdmissionCaseMappingDTO ipAdmissionCaseMappingDTO);

    List<IpAdmissionCaseMappingDTO> ipAdmissionCaseMappingsToIpAdmissionCaseMappingDTOs(List<IpAdmissionCaseMapping> ipAdmissionCaseMappings);

    List<IpAdmissionCaseMapping> ipAdmissionCaseMappingDTOsToIpAdmissionCaseMappings(List<IpAdmissionCaseMappingDTO> ipAdmissionCaseMappingDTOs);

    IpAdmissionDTO ipAdmissionToIpAdmissionDTO(IpAdmission ipAdmission);

    IpAdmission ipAdmissionDTOToIpAdmission(IpAdmissionDTO ipAdmissionDTO);

    List<IpAdmissionDTO> ipAdmissionsToIpAdmissionDTOs(List<IpAdmission> ipAdmissions);

    List<IpAdmission> ipAdmissionDTOsToIpAdmissions(List<IpAdmissionDTO> ipAdmissionDTOs);
}
