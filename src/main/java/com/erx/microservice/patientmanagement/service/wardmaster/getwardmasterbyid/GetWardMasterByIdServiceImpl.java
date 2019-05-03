package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid;

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

@Service("getWardMasterByIdService")
public class GetWardMasterByIdServiceImpl implements GetWardMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetWardMasterByIdServiceImpl.class);

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private WardMasterMapper wardMasterMapper;

    public GetWardMasterByIdServiceImpl(WardMasterRepository wardMasterRepository,
                                        WardMasterMapper wardMasterMapper) {
        this.wardMasterRepository = wardMasterRepository;
        this.wardMasterMapper = wardMasterMapper;
    }

    //constructor

    @Override
    public GetWardMasterByIdServiceResponse execute(GetWardMasterByIdServiceRequest request) throws ServiceException {

        GetWardMasterByIdServiceResponse response = null;
        WardMaster wardMaster = null;
        WardMasterByIdDTO wardMasterByIdDTO = new WardMasterByIdDTO();
        try {
            log.debug("Call to retrieve WardMaster with id : " + request.getWardMasterId());
            if (request.getWardMasterId() != 0) {
                //retrieve the WardMaster
                wardMaster = wardMasterRepository.findOne(request.getWardMasterId());
                //convert domain to DTO
                wardMasterByIdDTO.setId(wardMaster.getId());
                wardMasterByIdDTO.setWardCode(wardMaster.getWardCode());
                wardMasterByIdDTO.setWardName(wardMaster.getWardName());
                wardMasterByIdDTO.setStatus(wardMaster.isStatus());
                //set clinicLocation id
                wardMasterByIdDTO.setClinicLocationId(wardMaster.getClinicLocationId());
                wardMasterByIdDTO.setDepartmentId(wardMaster.getDepartment().getId());
                wardMasterByIdDTO.setDepartmentName(wardMaster.getDepartment().getDepartmentName());
                wardMasterByIdDTO.setIndentDepartmentId(wardMaster.getIndentDepartment().getId());
                wardMasterByIdDTO.setIndentDepartmentName(wardMaster.getIndentDepartment().getDepartmentName());
                wardMasterByIdDTO.setIpRequestDepartmentId(wardMaster.getIpRequestDepartment().getId());
                wardMasterByIdDTO.setIpRequestDepartmentName(wardMaster.getIpRequestDepartment().getDepartmentName());
            }
            //create response
            response = new GetWardMasterByIdServiceResponse(wardMasterByIdDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved WardMaster Successfully");
            log.debug("Retrieved WardMaster Successfully");
        } catch (Exception e) {
            //create response
            response = new GetWardMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve wardMaster Successfully");
            log.error("Failed to retrieve wardMaster Successfully");
        }
        return response;
    }
}
