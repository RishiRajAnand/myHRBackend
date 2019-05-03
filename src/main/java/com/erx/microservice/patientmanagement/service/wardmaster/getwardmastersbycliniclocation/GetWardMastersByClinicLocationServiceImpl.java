package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByIdDTO;
import com.erx.microservice.patientmanagement.service.mapper.WardMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getWardMastersByClinicLocationService")
public class GetWardMastersByClinicLocationServiceImpl implements GetWardMastersByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetWardMastersByClinicLocationServiceImpl.class);

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private WardMasterMapper wardMasterMapper;

    //create constructor
    public GetWardMastersByClinicLocationServiceImpl(WardMasterRepository wardMasterRepository,
                                                     WardMasterMapper wardMasterMapper) {
        this.wardMasterRepository = wardMasterRepository;
        this.wardMasterMapper = wardMasterMapper;
    }

    @Override
    public GetWardMastersByClinicLocationServiceResponse execute(GetWardMastersByClinicLocationServiceRequest request) throws ServiceException {

        GetWardMastersByClinicLocationServiceResponse response = null;
        List<WardMaster> wardMasters = null;
        List<WardMasterByIdDTO> wardMasterByIdDTOs = new ArrayList<>();
        try {
            log.debug("Call to retrieve WardMasters for the given ClinicLocation");
            if (request.getClinicLocationId() != 0 || request.getClinicLocationId() != null) {
                //retrieve WardMasters
                if(request.getIsActive().isPresent())
                    wardMasters = wardMasterRepository.findByClinicLocationIdAndStatusAndRecordStatus(
                            request.getClinicLocationId(),request.getIsActive().get(),1);
                else
                    wardMasters = wardMasterRepository.getWardMastersByClinicLocation(request.getClinicLocationId());
                for (WardMaster wardMaster : wardMasters) {
                    //create and set the object
                    WardMasterByIdDTO wardMasterByIdDTO = new WardMasterByIdDTO();
                    wardMasterByIdDTO.setId(wardMaster.getId());
                    wardMasterByIdDTO.setWardCode(wardMaster.getWardCode());
                    wardMasterByIdDTO.setWardName(wardMaster.getWardName());
                    wardMasterByIdDTO.setClinicLocationId(wardMaster.getClinicLocationId());
                    wardMasterByIdDTO.setStatus(wardMaster.isStatus());
                    wardMasterByIdDTO.setDepartmentId(wardMaster.getDepartment().getId());
                    wardMasterByIdDTO.setDepartmentName(wardMaster.getDepartment().getDepartmentName());
                    wardMasterByIdDTO.setIndentDepartmentId(wardMaster.getIndentDepartment().getId());
                    wardMasterByIdDTO.setIndentDepartmentName(wardMaster.getIndentDepartment().getDepartmentName());
                    wardMasterByIdDTO.setIpRequestDepartmentId(wardMaster.getIpRequestDepartment().getId());
                    wardMasterByIdDTO.setIpRequestDepartmentName(wardMaster.getIpRequestDepartment().getDepartmentName());


                    //add to the list
                    wardMasterByIdDTOs.add(wardMasterByIdDTO);
                }
            }
            response = new GetWardMastersByClinicLocationServiceResponse(wardMasterByIdDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved WardMasters Successfully");
            log.debug("Retrieved WardMasters Successfully");
        } catch (Exception e) {
            response = new GetWardMastersByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters");
            log.error("Failed to retrieve WardMasters");
        }
        return response;
    }
}
