package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Latha on 19-08-2018
* */

import com.erx.microservice.patientmanagement.domain.Vital;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.VitalsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface VitalMapper {

    VitalsDTO vitalToVitalsDTO(Vital vital);

    Vital vitalDTOToVital(VitalsDTO vitalsDTO);

    List<VitalsDTO> vitalToVitalsDTOs(List<Vital> wardMasters);

    List<Vital> vitalsDTOsToVital(List<VitalsDTO> wardMasterDTOs);
}
