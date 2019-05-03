package com.erx.microservice.patientmanagement.service.mapper.casemanagement;

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.service.dto.PatientCaseDTO;
import org.mapstruct.Mapper;

import java.util.List;

/*
* created by Latha on 29-11-2017
* */

@Mapper(componentModel = "spring", uses = {})
public interface CmMasterMapper {

    PatientCaseDTO cmMasterToPatientCaseDTO(CmMaster cmMaster);

    CmMaster cmMasterDTOToCmMaster(PatientCaseDTO patientCaseDTO);

    List<PatientCaseDTO> cmMastersToCmMasterDTOs(List<CmMaster> cmMasters);

    List<CmMaster> cmMasterDTOsToCmMasters(List<PatientCaseDTO> patientCaseDTOs);
}
