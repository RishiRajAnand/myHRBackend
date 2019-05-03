package com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid;

/*
* created by Latha on 02-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatment;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentRepository;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmPathyaPathyaDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("getPathyaPathyaByCmTreatmentIdService")
public class GetPathyaPathyaByCmTreatmentIdServiceImpl implements GetPathyaPathyaByCmTreatmentIdService {

    private final Logger log = LoggerFactory.getLogger(GetPathyaPathyaByCmTreatmentIdServiceImpl.class);

    @Autowired
    private CmTreatmentRepository cmTreatmentRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetPathyaPathyaByCmTreatmentIdServiceResponse execute(GetPathyaPathyaByCmTreatmentIdServiceRequest request) throws ServiceException {
        GetPathyaPathyaByCmTreatmentIdServiceResponse response = null;
        CmPathyaPathyaDTO cmPathyaPathyaDTO = new CmPathyaPathyaDTO();
        try {
            log.debug("Call to get cm treatment pathya pathya details");
            // retrieve pathya pathya by case id
            cmPathyaPathyaDTO = getCompleteCaseDetails.getPathyaPathya(request.getCaseId());
            if(cmPathyaPathyaDTO != null)
            // setting the dto to response
            response = new GetPathyaPathyaByCmTreatmentIdServiceResponse(cmPathyaPathyaDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm treatment pathya pathya details by cm master details id Successfully");
            log.debug("Retrieved cm treatment pathya pathya details by cm master details id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm treatment pathya pathya details by cm master details id" + e.getMessage());
            log.error("Failed to retrieve cm treatment pathya pathya details by cm master details id" + e.getMessage());
        }
        return response;
    }
}
