package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosagevalue;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmDosageValueMapping;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmDosageValueMappingRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmDosageValueMappingDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getCmDosageValueService")
public class GetCmDosageValueServiceImpl implements GetCmDosageValueService {

    private final Logger log = LoggerFactory.getLogger(GetCmDosageValueServiceImpl.class);

    @Autowired
    private CmDosageValueMappingRepository cmDosageValueMappingRepository;

    @Override
    public GetCmDosageValueServiceResponse execute() throws ServiceException {

        List<CmDosageValueMappingDTO> cmDosageValueMappingDTOs = new ArrayList<>();
        List<CmDosageValueMapping> cmDosageValueMappings = null;
        GetCmDosageValueServiceResponse response = null;
        try {
            log.debug("Call to get cm dosage value mapping");
            // retrieve cm dosage value mapping
            cmDosageValueMappings = cmDosageValueMappingRepository.findAll();
            if(cmDosageValueMappings != null)
                for(CmDosageValueMapping cmDosageValueMapping : cmDosageValueMappings) {
                    CmDosageValueMappingDTO cmDosageValueMappingDTO = new CmDosageValueMappingDTO();
                    cmDosageValueMappingDTO.setCmDosageValueMappingId(cmDosageValueMapping.getId());
                    cmDosageValueMappingDTO.setDosageName(cmDosageValueMapping.getCmDosageMaster().getDosageName());
                    cmDosageValueMappingDTO.setDosageValue(cmDosageValueMapping.getCmDosageValue().getDosageValue());
                    cmDosageValueMappingDTO.setDosageNumber(cmDosageValueMapping.getDosageNumber());
                    cmDosageValueMappingDTO.setPatientInstructions(cmDosageValueMapping.getCmDosageMaster().getDescription());
                    cmDosageValueMappingDTOs.add(cmDosageValueMappingDTO);
                }
            // setting the dto to response
            response = new GetCmDosageValueServiceResponse(cmDosageValueMappingDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm dosage value details Successfully");
            log.debug("Retrieved cm dosage value details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm dosage value details");
            log.error("Failed to retrieve cm dosage value details");
        }

        return response;
    }
}
