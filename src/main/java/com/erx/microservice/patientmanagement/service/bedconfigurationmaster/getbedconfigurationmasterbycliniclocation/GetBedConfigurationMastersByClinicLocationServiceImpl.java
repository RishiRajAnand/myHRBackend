package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterByIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedConfigurationMastersByClinicLocationService")
public class GetBedConfigurationMastersByClinicLocationServiceImpl implements GetBedConfigurationMastersByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetBedConfigurationMastersByClinicLocationServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    public GetBedConfigurationMastersByClinicLocationServiceImpl(BedConfigurationMasterRepository bedConfigurationMasterRepository) {
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
    }

    //constructor

    @Override
    public GetBedConfigurationMastersByClinicLocationServiceResponse execute(GetBedConfigurationMastersByClinicLocationServiceRequest request) throws ServiceException {

        GetBedConfigurationMastersByClinicLocationServiceResponse response = null;
        List<BedConfigurationMaster> bedConfigurationMasters = null;
        List<BedConfigurationMasterByIdDTO> bedConfigurationMasterByIdDTOs = new ArrayList<>();
        try {
            log.debug("Call to Retrieve all the bedConfigurationMasters");
            if (request.getClinicLocationId() != 0) {
                //retrieve bedConfigurationMasters
                bedConfigurationMasters = bedConfigurationMasterRepository
                        .getBedConfigurationMastersByClinicLocation(request.getClinicLocationId());
                if (bedConfigurationMasters.size() != 0) {
                    //set the list of DTO
                    for (BedConfigurationMaster bedConfigurationMaster : bedConfigurationMasters) {
                        BedConfigurationMasterByIdDTO bedConfigurationMasterByIdDTO = new BedConfigurationMasterByIdDTO();
                        //set bedConfigurationMasterByIdDTO
                        bedConfigurationMasterByIdDTO = new BedConfigurationMasterByIdDTO();
                        bedConfigurationMasterByIdDTO.setId(bedConfigurationMaster.getId());
                        bedConfigurationMasterByIdDTO.setBedCode(bedConfigurationMaster.getBedCode());
                        bedConfigurationMasterByIdDTO.setBedNumber(bedConfigurationMaster.getBedNumber());
                        bedConfigurationMasterByIdDTO.setBedTypeMasterId(bedConfigurationMaster.getBedTypeMaster().getId());
                        bedConfigurationMasterByIdDTO.setBedTypeName(bedConfigurationMaster.getBedTypeMaster().getBedTypeName());
                        bedConfigurationMasterByIdDTO.setWardMasterId(bedConfigurationMaster.getWardMaster().getId());
                        bedConfigurationMasterByIdDTO.setWardName(bedConfigurationMaster.getWardMaster().getWardName());
                        bedConfigurationMasterByIdDTO.setRoomConfigurationMasterId(bedConfigurationMaster.getRoomConfigurationMaster().getId());
                        bedConfigurationMasterByIdDTO.setRoomNumber(bedConfigurationMaster.getRoomConfigurationMaster().getRoomNumber());
                        bedConfigurationMasterByIdDTO.setStatus(bedConfigurationMaster.isStatus());
                        bedConfigurationMasterByIdDTO.setClinicLocationId(bedConfigurationMaster.getClinicLocationId());

                        //add to list
                        bedConfigurationMasterByIdDTOs.add(bedConfigurationMasterByIdDTO);
                    }
                }
            }
            //create response
            response = new GetBedConfigurationMastersByClinicLocationServiceResponse(bedConfigurationMasterByIdDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all BedConfigurationMasters");
            log.debug("Retrieved all BedConfigurationMasters");
        } catch (Exception e) {
            //create response
            response = new GetBedConfigurationMastersByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved all BedConfigurationMasters");
            log.error("Not retrieved all BedConfigurationMasters");
        }
        return response;
    }
}
