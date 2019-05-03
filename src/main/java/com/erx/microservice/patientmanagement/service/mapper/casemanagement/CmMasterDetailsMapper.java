package com.erx.microservice.patientmanagement.service.mapper.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterDetailsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CmMasterDetailsMapper {

    CmMasterDetailsDTO cmMasterDetailsToCmMasterDetailsDTO(CmMasterDetails cmMasterDetails);

    CmMasterDetails cmMasterDetailsDTOToCmMasterDetails(CmMasterDetailsDTO cmMasterDetailsDTO);

    List<CmMasterDetailsDTO> cmMasterDetailsToCmMasterDetailsDTOs(List<CmMasterDetails> cmMasterDetails);

    List<CmMasterDetails> cmMasterDetailsDTOsToCmMasterDetails(List<CmMasterDetailsDTO> getComplaintsDTOs);
}
