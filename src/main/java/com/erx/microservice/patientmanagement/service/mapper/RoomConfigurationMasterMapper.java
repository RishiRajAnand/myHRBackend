package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;
import com.erx.microservice.patientmanagement.service.dto.DepartmentMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface RoomConfigurationMasterMapper {

    RoomConfigurationMasterDTO roomConfigurationMasterToRoomConfigurationMasterDTO(RoomConfigurationMaster roomConfigurationMaster);

    RoomConfigurationMaster roomConfigurationMasterDTOToRoomConfigurationMaster(RoomConfigurationMasterDTO roomConfigurationMasterDTO);

    List<RoomConfigurationMasterDTO> roomConfigurationMastersToRoomConfigurationMasterDTOs(List<RoomConfigurationMaster> roomConfigurationMasters);

    List<RoomConfigurationMaster> roomConfigurationMasterDTOsToRoomConfigurationMasters(List<RoomConfigurationMasterDTO> roomConfigurationMasterDTOs);

    //ClinicLocation

    ClinicLocationDTO clinicLocationToClinicLocationDTO(ClinicLocation clinicLocation);

    ClinicLocation clinicLocationDTOToClinicLocation(ClinicLocationDTO clinicLocationDTO);

    List<ClinicLocationDTO> clinicLocationsToClinicLocationDTOs(List<ClinicLocation> clinicLocations);

    List<ClinicLocation> clinicLocationDTOsToClinicLocations(List<ClinicLocationDTO> clinicLocationDTOs);

    //BedTypeMaster

    BedTypeMasterDTO bedTypeMasterToBedTypeMasterDTO(BedTypeMaster bedTypeMaster);

    BedTypeMaster bedTypeMasterDTOToBedTypeMaster(BedTypeMasterDTO bedTypeMasterDTO);

    List<BedTypeMasterDTO> bedTypeMastersToBedTypeMasterDTOs(List<BedTypeMaster> bedTypeMasters);

    List<BedTypeMaster> bedTypeMasterDTOsToBedTypeMasters(List<BedTypeMasterDTO> bedTypeMasterDTOs);

    //WardMaster

    WardMasterDTO wardMasterToWardMasterDTO(WardMaster wardMaster);

    WardMaster wardMasterDTOToWardMaster(WardMasterDTO wardMasterDTO);

    List<WardMasterDTO> wardMastersToWardMasterDTOs(List<WardMaster> wardMasters);

    List<WardMaster> wardMasterDTOsToWardMasters(List<WardMasterDTO> wardMasterDTOs);

    //DepartmentMaster

    DepartmentMasterDTO departmentMasterToDepartmentMasterDTO(DepartmentMaster departmentMaster);

    DepartmentMaster departmentMasterDTOToDepartmentMaster(DepartmentMasterDTO departmentMasterDTO);

    List<DepartmentMasterDTO> departmentMastersToDepartmentMasterDTOs(List<DepartmentMaster> departmentMasters);

    List<DepartmentMaster> departmentMasterDTOsToDepartmentMasters(List<DepartmentMasterDTO> departmentMasterDTOs);

}
