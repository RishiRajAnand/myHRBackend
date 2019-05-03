package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmTemplateResponseDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getCmMedicineTemplateService")
public class GetCmMedicineTemplateServiceImpl implements GetCmMedicineTemplateService {

    private final Logger log = LoggerFactory.getLogger(GetCmMedicineTemplateServiceImpl.class);

    @Autowired
    private CmTemplateRepository cmTemplateRepository;

    @Override
    public GetCmMedicineTemplateServiceResponse execute(GetCmMedicineTemplateServiceRequest request) throws ServiceException {
        GetCmMedicineTemplateServiceResponse response = null;
        List<CmTemplate> cmTemplate = new ArrayList<CmTemplate>();
        List<CmTemplateResponseDTO> cmTemplateResponseDTOs = new ArrayList<CmTemplateResponseDTO>();
        try {
            log.debug("Call to get cm medicine templates" + request.getClinicId());
            cmTemplate = cmTemplateRepository.findCmTemplateByClinicAndScienceOfMedicine(request.getClinicId(),request.getScienceOfMedicineId());
            if(cmTemplate != null)
                for(CmTemplate cmTemplates : cmTemplate){
                    CmTemplateResponseDTO cmTemplateResponseDTO = new CmTemplateResponseDTO();
                    cmTemplateResponseDTO.setCmTemplateId(cmTemplates.getId());
                    cmTemplateResponseDTO.setName(cmTemplates.getName());
                    cmTemplateResponseDTO.setInstruction(cmTemplates.getInstructions());
                    cmTemplateResponseDTOs.add(cmTemplateResponseDTO);
                }
        // setting the dto to response
        response = new GetCmMedicineTemplateServiceResponse(cmTemplateResponseDTOs);
        response.SUCCESSFUL = true;
        response.setMessage("Retrieved cm medicine template Successfully");
        log.debug("Retrieved cm medicine template Successfully");
    } catch (Exception e) {
        response.SUCCESSFUL = false;
        response.setMessage("Failed to retrieve cm medicine template");
        log.error("Failed to retrieve cm medicine template");
    }
        return response;
    }
}
