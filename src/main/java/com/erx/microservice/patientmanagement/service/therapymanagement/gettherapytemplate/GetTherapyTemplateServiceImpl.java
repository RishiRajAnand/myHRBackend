package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateRepository;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateGetDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getTherapyTemplateService")
public class GetTherapyTemplateServiceImpl implements GetTherapyTemplateService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyTemplateServiceImpl.class);

    @Autowired
    private TherapyTemplateRepository therapyTemplateRepository;

    @Override
    public GetTherapyTemplateServiceResponse execute(GetTherapyTemplateServiceRequest request) throws ServiceException {
        GetTherapyTemplateServiceResponse response = null;
        List<TherapyTemplate> therapyTemplates = new ArrayList<TherapyTemplate>();
        List<TherapyTemplateGetDTO> therapyTemplateGetDTOs = new ArrayList<TherapyTemplateGetDTO>();
        try {
            log.debug("Call to get therapy template");
            therapyTemplates = therapyTemplateRepository.findTemplateByClinic(request.getClinicId());
            if(therapyTemplates != null)
                for(TherapyTemplate therapyTemplate : therapyTemplates){
                    TherapyTemplateGetDTO therapyTemplateGetDTO = new TherapyTemplateGetDTO();
                    therapyTemplateGetDTO.setTherapyTemplateId(therapyTemplate.getId());
                    therapyTemplateGetDTO.setName(therapyTemplate.getName());
                    therapyTemplateGetDTO.setTherapyGroupName(therapyTemplate.getTherapyGroup());
                   // therapyTemplateGetDTO.setSpecialInstruction(therapyTemplate.getSpecialInstruction());
                    therapyTemplateGetDTOs.add(therapyTemplateGetDTO);
                }
            // setting the dto to response
            response = new GetTherapyTemplateServiceResponse(therapyTemplateGetDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy template Successfully");
            log.debug("Retrieved therapy template Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy template");
            log.error("Failed to retrieve therapy template");
        }
        return response;
    }
}
