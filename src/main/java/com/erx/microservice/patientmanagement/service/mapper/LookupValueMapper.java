package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.service.dto.LookupValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for the entity LookupValue and its DTO LookupValueDTO.
 */
@Mapper(componentModel = "spring", uses = {LookupMapper.class})
public interface LookupValueMapper  {


    LookupValueDTO lookupValueToLookupValueDTO(LookupValue lookupValue);

    LookupValue lookupValueDTOToLookupValue(LookupValueDTO lookupValueDTO);

    List<LookupValueDTO> lookupValuesToLookupValueDTOs(List<LookupValue> lookupValues);

    List<LookupValue> lookupValueDTOsToLookupValues(List<LookupValueDTO> lookupValueDTOs);


}
