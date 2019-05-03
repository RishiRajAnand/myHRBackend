package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid;

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

@Service("getBedConfigurationMasterService")
public class GetBedConfigurationMasterByIdServiceImpl implements GetBedConfigurationMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetBedConfigurationMasterByIdServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    public GetBedConfigurationMasterByIdServiceImpl(BedConfigurationMasterRepository bedConfigurationMasterRepository) {
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
    }

    @Override
    public GetBedConfigurationMasterByIdServiceResponse execute(GetBedConfigurationMasterByIdServiceRequest request) throws ServiceException {

        GetBedConfigurationMasterByIdServiceResponse response = null;
        BedConfigurationMaster bedConfigurationMaster = null;
        BedConfigurationMasterByIdDTO bedConfigurationMasterByIdDTO = null;
        try {
            log.debug("Call to Retrieve BedConfigurationMaster for the given id : " + request.getBedConfigurationMasterId());
            if (request.getBedConfigurationMasterId() != 0) {
                //retrieve the BedConfigurationMaster
                bedConfigurationMaster = bedConfigurationMasterRepository.findOne(request.getBedConfigurationMasterId());
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
            }
            //create response
            response = new GetBedConfigurationMasterByIdServiceResponse(bedConfigurationMasterByIdDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved BedConfigurationMaster Successfully");
            log.debug("Retrieved BedConfigurationMaster Successfully");
        } catch (Exception e) {
            //create response
            response = new GetBedConfigurationMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved BedConfigurationMaster Successfully");
            log.error("Not retrieved BedConfigurationMaster Successfully");
        }
        return response;
    }
}
