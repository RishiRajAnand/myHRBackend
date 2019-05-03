package com.erx.microservice.patientmanagement.service.mapper;

import com.erx.microservice.patientmanagement.domain.Lookup;
import com.erx.microservice.patientmanagement.service.dto.LookupDTO;

import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity Lookup and its DTO LookupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LookupMapper {

    LookupDTO lookupToLookupDTO(Lookup lookupValue);

    Lookup lookupDTOToLookup(LookupDTO lookupValueDTO);

    List<LookupDTO> lookupsToLookupDTOs(List<Lookup> lookups);

    List<Lookup> lookupDTOsToLookupValues(List<LookupDTO> lookupDTOs);



}
