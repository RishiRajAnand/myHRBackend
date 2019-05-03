package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmInvestigationGetDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMedicineTreatmentResponseDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("getInvestigationByCaseIdService")
public class GetInvestigationByCaseIdServiceImpl implements GetInvestigationByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetInvestigationByCaseIdServiceImpl.class);


    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetInvestigationByCaseIdServiceResponse execute(GetInvestigationByCaseIdServiceRequest request) throws ServiceException {
        GetInvestigationByCaseIdServiceResponse response = null;
        CmInvestigationGetDTO cmInvestigationGetDTO = new CmInvestigationGetDTO();
        try {
            log.debug("Call to get cm investigation by case id" + request.getCaseId());
            cmInvestigationGetDTO = getCompleteCaseDetails.getCmInvestigation(request.getCaseId());
            // setting the dto to response
            response = new GetInvestigationByCaseIdServiceResponse(cmInvestigationGetDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm investigation by case id Successfully");
            log.debug("Retrieved cm investigation by case id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm investigation by case id");
            log.error("Failed to retrieve cm investigation by case id");
        }
        return response;
    }
}
