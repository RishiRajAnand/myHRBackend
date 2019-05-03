package com.erx.microservice.patientmanagement.service.casemanagement.geticdmaster;

/*
* created by Latha on 18-09-2018
* */


import com.erx.microservice.patientmanagement.domain.casemanagement.CmIcdMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmIcdMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.IcdMasterDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cmIcdMasterService")
public class CmIcdMasterServiceImpl implements CmIcdMasterService {

    private final Logger log = LoggerFactory.getLogger(CmIcdMasterServiceImpl.class);

    @Autowired
    private CmIcdMasterRepository cmIcdMasterRepository;

    @Override
    public CmIcdMasterServiceResponse execute() throws ServiceException {

        List<IcdMasterDTO> icdMasterDTOs = new ArrayList<>();
        List<CmIcdMaster> cmIcdMasters = null;
        CmIcdMasterServiceResponse response = null;
        try {
            log.debug("Call to get acd master");
            // retrieve acd master
            cmIcdMasters = cmIcdMasterRepository.findAll();
            if(cmIcdMasters != null)
                for(CmIcdMaster cmIcdMaster : cmIcdMasters) {
                    IcdMasterDTO icdMasterDTO = new IcdMasterDTO();
                    icdMasterDTO.setIcdMasterId(cmIcdMaster.getId());
                    icdMasterDTO.setName(cmIcdMaster.getIcdCode());
                    icdMasterDTO.setDescription(cmIcdMaster.getIcdDescription());
                    icdMasterDTOs.add(icdMasterDTO);
                }
            // setting the dto to response
            response = new CmIcdMasterServiceResponse(icdMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved icd master details Successfully");
            log.debug("Retrieved icd master details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve icd master master details");
            log.error("Failed to retrieve icd master master details");
        }
        return response;
    }
}
