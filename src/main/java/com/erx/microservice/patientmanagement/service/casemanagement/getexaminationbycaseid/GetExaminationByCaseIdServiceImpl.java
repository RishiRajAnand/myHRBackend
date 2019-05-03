package com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetCmExaminationDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getExaminationByCaseIdService")
public class GetExaminationByCaseIdServiceImpl implements GetExaminationByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetExaminationByCaseIdServiceImpl.class);

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetExaminationByCaseIdServiceResponse execute(GetExaminationByCaseIdServiceRequest request) throws ServiceException {
        List<GetCmExaminationDTO> getCmExaminationDTOs = new ArrayList<>();
        GetExaminationByCaseIdServiceResponse response = null;
        try {
            log.debug("Call to get examination details by case id");
            // retrieve complaints by case id
            getCmExaminationDTOs = getCompleteCaseDetails.getExaminationDetails(request.getCaseId());
            // setting the dto to response
            response = new GetExaminationByCaseIdServiceResponse(getCmExaminationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved examination details Successfully");
            log.debug("Retrieved examination Details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve examination details");
            log.error("Failed to retrieve examination Details");
        }
        return response;
    }
}
