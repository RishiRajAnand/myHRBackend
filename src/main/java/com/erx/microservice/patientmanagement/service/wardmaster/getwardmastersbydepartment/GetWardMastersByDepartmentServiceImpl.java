package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment;

/*
* created by Brighty on 04-01-2017
* */


import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardByDepartmentInputDTO;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("getWardMastersByDepartmentService")
public class GetWardMastersByDepartmentServiceImpl implements GetWardMastersByDepartmentService {

    private final Logger log = LoggerFactory.getLogger(GetWardMastersByDepartmentServiceImpl.class);

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Override
    public GetWardMastersByDepartmentServiceResponse execute(GetWardMastersByDepartmentServiceRequest request) throws ServiceException {

        GetWardMastersByDepartmentServiceResponse response = new GetWardMastersByDepartmentServiceResponse();
        List<WardMaster> wardMasters = null;
        List<WardMasterByClinicLocationDTO> wardMastersOutput = null;
        List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs = new ArrayList<>();
        List<WardByDepartmentInputDTO> wardByDepartmentInputDTOs = null;
        try {
            log.debug("Call to retrieve the WardMasters by Department");
            if (request.getWardByDepartmentInputDTOs() != null) {
                //retrieve the input object
                wardByDepartmentInputDTOs = request.getWardByDepartmentInputDTOs();
                for (WardByDepartmentInputDTO input : wardByDepartmentInputDTOs) {
                    //retrieve the wardMasters
                    wardMasters = wardMasterRepository.getWardMasterByDepartment(input.getDepartmentId(), input.getClinicLocationId());
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
            }

            //Remove the duplicate entry using HashMap
            Map<Long, WardMasterByClinicLocationDTO> map = new HashMap<Long, WardMasterByClinicLocationDTO>();
            for (WardMasterByClinicLocationDTO wardMaster : wardMasterByClinicLocationDTOs) {
                Long key = wardMaster.getId();
                if (!map.containsKey(key)) {
                    map.put(key, wardMaster);
                }
            }
            //Convert HashMap to List
            wardMastersOutput = new ArrayList<WardMasterByClinicLocationDTO>(map.values());

            response.setWardMasterByClinicLocationDTOs(wardMastersOutput);
            response.setMessage("Retrieved WardMasters for the given department");
            response.SUCCESSFUL = true;
            log.debug("Retrieved WardMasters for the given department");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters for given Department");
            log.error("Failed to retrieve WardMasters for given Department");
        }
        return response;
    }
}




