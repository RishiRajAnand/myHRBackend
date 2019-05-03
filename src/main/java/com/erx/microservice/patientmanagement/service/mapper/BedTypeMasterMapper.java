package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BedTypeMasterMapper {

    BedTypeMasterDTO bedTypeMasterToBedTypeMasterDTO(BedTypeMaster bedTypeMaster);

    BedTypeMaster bedTypeMasterDTOToBedTypeMaster(BedTypeMasterDTO bedTypeMasterDTO);

    List<BedTypeMasterDTO> bedTypeMastersToBedTypeMasterDTOs(List<BedTypeMaster> bedTypeMasters);

    List<BedTypeMaster> bedTypeMasterDTOsToBedTypeMasters(List<BedTypeMasterDTO> bedTypeMasterDTOs);

    //ClinicLocation

    ClinicLocationDTO clinicLocationToClinicLocationDTO(ClinicLocation clinicLocation);

    ClinicLocation clinicLocationDTOToClinicLocation(ClinicLocationDTO clinicLocationDTO);

    List<ClinicLocationDTO> clinicLocationsToClinicLocationDTOs(List<ClinicLocation> clinicLocations);

    List<ClinicLocation> clinicLocationDTOsToClinicLocations(List<ClinicLocationDTO> clinicLocationDTOs);
}
