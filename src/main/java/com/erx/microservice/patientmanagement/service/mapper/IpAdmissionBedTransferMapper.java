package com.erx.microservice.patientmanagement.service.mapper;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import com.erx.microservice.patientmanagement.service.dto.BedTransferDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface IpAdmissionBedTransferMapper {

    BedTransferDTO ipAdmissionBedTransferToBedTransferDTO(IpAdmissionBedTransfer ipAdmissionBedTransfer);

    IpAdmissionBedTransfer bedTransferDTOToIpAdmissionBedTransfer(BedTransferDTO bedTransferDTO);

    List<BedTransferDTO> ipAdmissionBedTransfersToBedTransferDTOs(List<IpAdmissionBedTransfer> ipAdmissionBedTransfers);

    List<IpAdmissionBedTransfer> bedTransferDTOsToIpAdmissionBedTransfers(List<BedTransferDTO> bedTransferDTOs);

}
