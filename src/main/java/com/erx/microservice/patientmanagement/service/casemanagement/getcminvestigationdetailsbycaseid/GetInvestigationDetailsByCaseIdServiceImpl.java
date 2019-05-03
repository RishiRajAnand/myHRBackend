package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmInvestigationDetailsGetDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmInvestigationGetDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getInvestigationDetailsByCaseIdService")
public class GetInvestigationDetailsByCaseIdServiceImpl implements GetInvestigationDetailsByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetInvestigationDetailsByCaseIdServiceImpl.class);


    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetInvestigationDetailsByCaseIdServiceResponse execute(GetInvestigationDetailsByCaseIdServiceRequest request) throws ServiceException {
        GetInvestigationDetailsByCaseIdServiceResponse response = null;
        List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs = new ArrayList<>();
        try {
            log.debug("Call to get cm investigation details by case id" + request.getCaseId());
            cmInvestigationDetailsGetDTOs = getCompleteCaseDetails.getCmInvestigationDetails(request);
            // setting the dto to response
            response = new GetInvestigationDetailsByCaseIdServiceResponse(cmInvestigationDetailsGetDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm investigation details by case id Successfully");
            log.debug("Retrieved cm investigation by case id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm investigation details by case id");
            log.error("Failed to retrieve cm investigation details by case id");
        }
        return response;
    }
}
