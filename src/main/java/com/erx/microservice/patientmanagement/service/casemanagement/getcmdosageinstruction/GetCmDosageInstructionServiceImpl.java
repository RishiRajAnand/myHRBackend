package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosageinstruction;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmDosageInstruction;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmDosageInstructionRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmDosageInstructionDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getCmDosageInstructionService")
public class GetCmDosageInstructionServiceImpl implements GetCmDosageInstructionService {

    private final Logger log = LoggerFactory.getLogger(GetCmDosageInstructionServiceImpl.class);

    @Autowired
    private CmDosageInstructionRepository cmDosageInstructionRepository;

    @Override
    public GetCmDosageInstructionServiceResponse execute() throws ServiceException {

        List<CmDosageInstructionDTO> cmDosageInstructionDTOs = new ArrayList<>();
        List<CmDosageInstruction> cmDosageInstructions = null;
        GetCmDosageInstructionServiceResponse response = null;
        try {
            log.debug("Call to get cm dosage instruction details");
            // retrieve cm dosage instruction details
            cmDosageInstructions = cmDosageInstructionRepository.findAll();
            if(cmDosageInstructions != null)
                for(CmDosageInstruction cmDosageInstruction : cmDosageInstructions) {
                    CmDosageInstructionDTO cmDosageInstructionDTO = new CmDosageInstructionDTO();
                    cmDosageInstructionDTO.setCmDosageInstructionId(cmDosageInstruction.getId());
                    cmDosageInstructionDTO.setCmDosageInstruction(cmDosageInstruction.getDosageInstruction());
                    cmDosageInstructionDTO.setCmDosageDefaultTime(cmDosageInstruction.getDosageDefaultTime());
                    cmDosageInstructionDTOs.add(cmDosageInstructionDTO);
                }
            // setting the dto to response
            response = new GetCmDosageInstructionServiceResponse(cmDosageInstructionDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm dosage instruction details Successfully");
            log.debug("Retrieved cm dosage instruction Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm dosage instruction details");
            log.error("Failed to retrieve cm dosage instruction details");
        }
        return response;
    }
}
