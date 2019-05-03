package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters;

/*
* created by Brighty on 20-11-2017
* */


import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedConfigurationMastersService")
public class GetBedConfigurationMastersServiceImpl implements GetBedConfigurationMastersService {

    private final Logger log = LoggerFactory.getLogger(GetBedConfigurationMastersServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    public GetBedConfigurationMastersServiceImpl(BedConfigurationMasterRepository bedConfigurationMasterRepository) {
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
    }

    //constructor

    @Override
    public GetBedConfigurationMastersServiceResponse execute(GetBedConfigurationMastersServiceRequest request) throws ServiceException {

        GetBedConfigurationMastersServiceResponse response = null;
        List<BedConfigurationMasterByClinicLocationDTO> bedConfigurationMasterByClinicLocationDTOs = new ArrayList<>();
        List<BedConfigurationMaster> bedConfigurationMasters = null;
        try {
            log.debug("Call to retrieve the BedConfigurationMasters");
            if (request.getClinicLocationId() != 0) {
                //retrieve the BedConfigurationMasters
                bedConfigurationMasters = bedConfigurationMasterRepository.getBedConfigurationMastersByClinicLocation(request.getClinicLocationId());
                if (bedConfigurationMasters.size() != 0) {
                    //Convert domain to DTO
                    for (BedConfigurationMaster bedConfigurationMaster : bedConfigurationMasters) {
                        BedConfigurationMasterByClinicLocationDTO bedConfigurationMasterByClinicLocationDTO = new BedConfigurationMasterByClinicLocationDTO();
                        //set the values
                        bedConfigurationMasterByClinicLocationDTO.setId(bedConfigurationMaster.getId());
                        bedConfigurationMasterByClinicLocationDTO.setBedNumber(bedConfigurationMaster.getBedNumber());

                        //add to list
                        bedConfigurationMasterByClinicLocationDTOs.add(bedConfigurationMasterByClinicLocationDTO);
                    }
                }
            }
            //create response
            response = new GetBedConfigurationMastersServiceResponse(bedConfigurationMasterByClinicLocationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all BedConfigurationMasters");
            log.debug("Retrieved all BedConfigurationMasters");
        } catch (Exception e) {
            //create response
            response = new GetBedConfigurationMastersServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Failed to retrieve BedConfigurationMasters");
            log.error("Failed to retrieve BedConfigurationMasters");
        }
        return response;
    }
}
