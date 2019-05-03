package com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicPreference;
import com.erx.microservice.patientmanagement.repository.casemanagement.ClinicPreferenceRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ClinicPreferenceDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("getClinicPreferenceByClinicService")
public class GetClinicPreferenceByClinicServiceImpl implements GetClinicPreferenceByClinicService {

    private final Logger log = LoggerFactory.getLogger(GetClinicPreferenceByClinicServiceImpl.class);

    @Autowired
    ClinicPreferenceRepository clinicPreferenceRepository;

    @Override
    public GetClinicPreferenceByClinicServiceResponse execute(GetClinicPreferenceByClinicServiceRequest request) throws ServiceException {
        GetClinicPreferenceByClinicServiceResponse response = null;
        ClinicPreference clinicPreference = new ClinicPreference();
        ClinicPreferenceDTO clinicPreferenceDTO = new ClinicPreferenceDTO();
        try {
            log.debug("Call to get clinic preference details by clinic" + request.getClinicId());
            clinicPreference = clinicPreferenceRepository.getClinicPreferenceByClinic(request.getClinicId());
            if(clinicPreference != null)
                clinicPreferenceDTO.setExamIP(clinicPreference.getExamIP());
            clinicPreferenceDTO.setExamOP(clinicPreference.getExamOP());
            clinicPreferenceDTO.setGenerateLab(clinicPreference.isGenerateLab());
            clinicPreferenceDTO.setTherapyEstimate(clinicPreference.getTherapyEstimate());
            // setting the dto to response
            response = new GetClinicPreferenceByClinicServiceResponse(clinicPreferenceDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved clinic preference details by clinic Successfully");
            log.debug("Retrieved clinic preference Details by clinic Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve clinic preference details by clinic");
            log.error("Failed to retrieve clinic preference details by clinic" + e.getMessage());
        }
        return response;
    }
}
