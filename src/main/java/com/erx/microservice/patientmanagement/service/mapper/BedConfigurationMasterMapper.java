package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Brighty on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.service.dto.BedConfigurationDTO;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BedConfigurationMasterMapper {

    BedConfigurationDTO bedConfigurationMasterToBedConfigurationDTO(BedConfigurationMaster bedConfigurationMaster);

    BedConfigurationMaster bedConfigurationDTOToBedConfigurationMaster(BedConfigurationDTO bedConfigurationDTO);

    List<BedConfigurationDTO> bedConfigurationMastersToBedConfigurationDTOs(List<BedConfigurationMaster> bedConfigurationMasters);

    List<BedConfigurationMaster> bedConfigurationDTOsToBedConfigurationMasters(List<BedConfigurationDTO> bedConfigurationDTOs);

    BedConfigurationMasterDTO bedConfigurationMasterToBedConfigurationMasterDTO(BedConfigurationMaster bedConfigurationMaster);

    BedConfigurationMaster bedConfigurationMasterDTOToBedConfigurationMaster(BedConfigurationMasterDTO bedConfigurationMasterDTO);

    List<BedConfigurationMasterDTO> bedConfigurationMastersToBedConfigurationMasterDTOs(List<BedConfigurationMaster> bedConfigurationMasters);

    List<BedConfigurationMaster> bedConfigurationMasterDTOsToBedConfigurationMasters(List<BedConfigurationMasterDTO> bedConfigurationMasterDTOs);
}
