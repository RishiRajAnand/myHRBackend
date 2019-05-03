package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface IpAdmissionBedMovementMapper {

    BedMovementDTO ipAdmissionBedMovementToBedMovementDTO(IpAdmissionBedMovement ipAdmissionBedMovement);

    IpAdmissionBedMovement BedMovementDTOToIpAdmissionBedMovement(BedMovementDTO bedMovementDTO);

    List<BedMovementDTO> ipAdmissionBedMovementsToBedMovementDTOs(List<IpAdmissionBedMovement> ipAdmissionBedMovements);

    List<IpAdmissionBedMovement> bedMovementDTOsToIpAdmissionBedMovements(List<BedMovementDTO> bedMovementDTOs);

}
