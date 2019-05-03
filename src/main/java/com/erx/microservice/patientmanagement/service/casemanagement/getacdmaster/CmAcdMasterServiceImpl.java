package com.erx.microservice.patientmanagement.service.casemanagement.getacdmaster;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmAcdMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmAcdMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AcdMasterDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cmAcdMasterService")
public class CmAcdMasterServiceImpl implements CmAcdMasterService {

    private final Logger log = LoggerFactory.getLogger(CmAcdMasterServiceImpl.class);

    @Autowired
    private CmAcdMasterRepository cmAcdMasterRepository;

    @Override
    public CmAcdMasterServiceResponse execute() throws ServiceException {

        List<AcdMasterDTO> acdMasterDTOs = new ArrayList<>();
        List<CmAcdMaster> cmAcdMasters = null;
        CmAcdMasterServiceResponse response = null;
        try {
            log.debug("Call to get acd master");
            // retrieve acd master
            cmAcdMasters = cmAcdMasterRepository.findAll();
            if(cmAcdMasters != null)
                for(CmAcdMaster cmAcdMaster : cmAcdMasters) {
                    AcdMasterDTO acdMasterDTO = new AcdMasterDTO();
                    acdMasterDTO.setAcdMasterId(cmAcdMaster.getId());
                    acdMasterDTO.setName(cmAcdMaster.getAcdCode());
                    acdMasterDTO.setDescription(cmAcdMaster.getAcdDescription());
                    acdMasterDTOs.add(acdMasterDTO);
                }
            // setting the dto to response
            response = new CmAcdMasterServiceResponse(acdMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved acd master details Successfully");
            log.debug("Retrieved acd master details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve acd master master details");
            log.error("Failed to retrieve acd master master details");
        }
        return response;
    }
}
