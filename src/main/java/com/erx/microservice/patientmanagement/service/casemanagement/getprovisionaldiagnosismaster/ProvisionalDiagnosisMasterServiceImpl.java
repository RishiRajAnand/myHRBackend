package com.erx.microservice.patientmanagement.service.casemanagement.getprovisionaldiagnosismaster;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.ProvisionalDiagnosisMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.ProvisionalDiagnosisMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ProvisionalDiagnosisMasterDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("provisionalDiagnosisMasterService")
public class ProvisionalDiagnosisMasterServiceImpl implements ProvisionalDiagnosisMasterService {

    private final Logger log = LoggerFactory.getLogger(ProvisionalDiagnosisMasterServiceImpl.class);

    @Autowired
    private ProvisionalDiagnosisMasterRepository provisionalDiagnosisMasterRepository;

    @Override
    public ProvisionalDiagnosisMasterServiceResponse execute() throws ServiceException {

        List<ProvisionalDiagnosisMasterDTO> provisionalDiagnosisMasterDTOs = new ArrayList<>();
        List<ProvisionalDiagnosisMaster> provisionalDiagnosisMasters = null;
        ProvisionalDiagnosisMasterServiceResponse response = null;
        try {
            log.debug("Call to get provisional diagnosis master");
            // retrieve provisional diagnosis
            provisionalDiagnosisMasters = provisionalDiagnosisMasterRepository.findAll();
            if(provisionalDiagnosisMasters != null)
                for(ProvisionalDiagnosisMaster provisionalDiagnosisMaster : provisionalDiagnosisMasters) {
                    ProvisionalDiagnosisMasterDTO provisionalDiagnosisMasterDTO = new ProvisionalDiagnosisMasterDTO();
                    provisionalDiagnosisMasterDTO.setProvisionalDiagnosisId(provisionalDiagnosisMaster.getId());
                    provisionalDiagnosisMasterDTO.setName(provisionalDiagnosisMaster.getName());
                    provisionalDiagnosisMasterDTOs.add(provisionalDiagnosisMasterDTO);
                }
            // setting the dto to response
            response = new ProvisionalDiagnosisMasterServiceResponse(provisionalDiagnosisMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved provisional diagnosis master details Successfully");
            log.debug("Retrieved provisional diagnosis master details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve provisional diagnosis master details");
            log.error("Failed to retrieve provisional diagnosis master details");
        }
        return response;
    }
}
