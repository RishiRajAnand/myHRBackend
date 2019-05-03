package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by brighty on 09-11-2017.
 */

@Mapper(componentModel = "spring", uses = {})
public interface ClinicLocationMapper {

    ClinicLocationDTO clinicLocationToClinicLocationDTO(ClinicLocation clinicLocation);

    ClinicLocation clinicLocationDTOToClinicLocation(ClinicLocationDTO clinicLocationDTO);

    List<ClinicLocationDTO> clinicLocationsToClinicLocationDTOs(List<ClinicLocation> clinicLocations);

    List<ClinicLocation> clinicLocationDTOsToClinicLocations(List<ClinicLocationDTO> clinicLocationDTOs);

}
