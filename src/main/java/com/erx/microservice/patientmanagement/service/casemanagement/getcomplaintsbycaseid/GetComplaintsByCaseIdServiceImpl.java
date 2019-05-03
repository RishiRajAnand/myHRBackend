package com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetComplaintsDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getComplaintsByCaseIdService")
public class GetComplaintsByCaseIdServiceImpl implements GetComplaintsByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetComplaintsByCaseIdServiceImpl.class);

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public  GetComplaintsByCaseIdServiceResponse execute(GetComplaintsByCaseIdServiceRequest request) throws ServiceException {
        GetComplaintsDTO getComplaintsDTO = null;
        GetComplaintsByCaseIdServiceResponse response = null;
        try {
            log.debug("Call to get complaints by case id");
            // retrieve complaints by case id
            getComplaintsDTO = getCompleteCaseDetails.getComplaints(request.getCaseId());
            // setting the dto to response
            response = new GetComplaintsByCaseIdServiceResponse(getComplaintsDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved complaints details Successfully");
            log.debug("Retrieved complaints Details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve complaints details");
            log.error("Failed to retrieve complaints Details");
        }

        return response;
    }
}
