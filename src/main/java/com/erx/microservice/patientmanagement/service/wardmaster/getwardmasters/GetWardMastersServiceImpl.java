package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getWardMastersService")
public class GetWardMastersServiceImpl implements GetWardMastersService {

    private final Logger log = LoggerFactory.getLogger(GetWardMastersServiceImpl.class);

    @Autowired
    private WardMasterRepository wardMasterRepository;

    public GetWardMastersServiceImpl(WardMasterRepository wardMasterRepository) {
        this.wardMasterRepository = wardMasterRepository;
    }

    //constructor

    @Override
    public GetWardMastersServiceResponse execute(GetWardMastersServiceRequest request) throws ServiceException {

        log.debug("call to get all the WardMasters for the given clinicLocation");
        GetWardMastersServiceResponse response = null;
        List<WardMaster> wardMasters = null;
        List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs = new ArrayList<>();

        try {
            if (request.getClinicLocationId() != 0) {
                //retrieve the list of BedTypeMaster
                wardMasters = wardMasterRepository.getWardMastersByClinicLocation(request.getClinicLocationId());
                //set the values in BedTypeMasterByClinicLocationDTO list
                for (WardMaster wardMaster : wardMasters) {
                    //create BedTypeMasterByClinicLocationDTO object
                    WardMasterByClinicLocationDTO wardMasterByClinicLocationDTO = new WardMasterByClinicLocationDTO();
                    //set the values
                    wardMasterByClinicLocationDTO.setId(wardMaster.getId());
                    wardMasterByClinicLocationDTO.setWardName(wardMaster.getWardName());

                    //add to the list
                    wardMasterByClinicLocationDTOs.add(wardMasterByClinicLocationDTO);
                }
            }
            //create response
            response = new GetWardMastersServiceResponse(wardMasterByClinicLocationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all the WardMasters for the given clinicLocation");
            log.debug("Retrieved all the WardMasters for the given clinicLocation");
        } catch (Exception e) {
            //create response
            response = new GetWardMastersServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved all the WardMasters for the given clinicLocation");
            log.error("Not retrieved all the WardMasters for the given clinicLocation");
        }
        return response;
    }
}
