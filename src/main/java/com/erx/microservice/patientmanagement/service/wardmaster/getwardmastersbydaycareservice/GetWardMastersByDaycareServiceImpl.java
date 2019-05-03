package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice;

/*
* created by Brighty on 10-01-18
* */


import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("getDaycareWardMastersService")
public class GetWardMastersByDaycareServiceImpl implements GetWardMastersByDaycareService {

    private final Logger log = LoggerFactory.getLogger(GetWardMastersByDaycareServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Override
    public GetWardMastersByDaycareServiceResponse execute(GetWardMastersByDaycareServiceRequest request) throws ServiceException {

        GetWardMastersByDaycareServiceResponse response = null;
        List<BedConfigurationMaster> bedConfigurationMasters = null;
        List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs = new ArrayList<>();
        List<WardMasterByClinicLocationDTO> foundWardMasterByClinicLocationDTOs = null;
        try {
            log.debug("Call to get Wards by Daycare is " + request.isDaycare());
            if (request.getClinicLocationId() != null) {
                //retrieve the wards
                bedConfigurationMasters = bedConfigurationMasterRepository
                        .getBedConfigurationByDaycare(request.getClinicLocationId(), request.isDaycare());
                //set the WardMasterByClinicLocationDTO
                for (BedConfigurationMaster bedConfigurationMaster : bedConfigurationMasters) {
                    WardMasterByClinicLocationDTO wardMasterByClinicLocationDTO = new WardMasterByClinicLocationDTO();
                    wardMasterByClinicLocationDTO.setId(bedConfigurationMaster.getWardMaster().getId());
                    wardMasterByClinicLocationDTO.setWardName(bedConfigurationMaster.getWardMaster().getWardName());
                    //add to list
                    wardMasterByClinicLocationDTOs.add(wardMasterByClinicLocationDTO);
                }
                //Remove the duplicate entry using HashMap
                Map<Long, WardMasterByClinicLocationDTO> map = new HashMap<Long, WardMasterByClinicLocationDTO>();
                for (WardMasterByClinicLocationDTO wardMasterByClinicLocationDTO : wardMasterByClinicLocationDTOs) {
                    Long key = wardMasterByClinicLocationDTO.getId();
                    if (!map.containsKey(key)) {
                        map.put(key, wardMasterByClinicLocationDTO);
                    }
                }
                //Convert HashMap to List
                foundWardMasterByClinicLocationDTOs = new ArrayList<WardMasterByClinicLocationDTO>(map.values());

                //create response
                response = new GetWardMastersByDaycareServiceResponse(foundWardMasterByClinicLocationDTOs);
                response.setMessage("Retrieved " + foundWardMasterByClinicLocationDTOs.size() + " Wards by Daycare is " + request.isDaycare());
                response.SUCCESSFUL = true;
                log.debug("Retrieved " + foundWardMasterByClinicLocationDTOs.size() + " Wards by Daycare is " + request.isDaycare());
            }

        } catch (Exception e) {
            response = new GetWardMastersByDaycareServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Wards by Daycare is " + request.isDaycare());
            log.error("Failed to retrieve Wards by Daycare is " + request.isDaycare() + e.getStackTrace());
        }
        return response;
    }
}
