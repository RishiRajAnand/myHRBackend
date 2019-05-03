package com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetCmPersonalHistoryDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getPersonalHistoryByCaseIdService")
public class GetPersonalHistoryByCaseIdServiceImpl implements GetPersonalHistoryByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetPersonalHistoryByCaseIdServiceImpl.class);

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetPersonalHistoryByCaseIdServiceResponse execute(GetPersonalHistoryByCaseIdServiceRequest request) throws ServiceException {
        List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs = new ArrayList<>();
        GetPersonalHistoryByCaseIdServiceResponse response = null;
        try {
            log.debug("Call to get personal history by case id");
            // retrieve complaints by case id
            getCmPersonalHistoryDTOs = getCompleteCaseDetails.getPersonalHistory(request.getCaseId());
            // setting the dto to response
            response = new GetPersonalHistoryByCaseIdServiceResponse(getCmPersonalHistoryDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved personal history details Successfully");
            log.debug("Retrieved personal history Details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve personal history details");
            log.error("Failed to retrieve personal history Details");
        }

        return response;
    }
}
