package com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid;



import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
* created by raushan on 28-08-2018
* */

@Service
public class GetCaseIdByOrderIdServiceImpl implements GetCaseIdByOrderIdService {

    private final Logger log = LoggerFactory.getLogger(GetCaseIdByOrderIdServiceImpl.class);

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Override
    public GetCaseIdByOrderIdServiceResponse execute(GetCaseIdByOrderIdServiceRequest request) throws ServiceException {

        GetCaseIdByOrderIdServiceResponse response = null;
        Long caseId=null;
        try {
            log.debug("Retrieving caseId  for given orderId");
            if (request.getOrderId() != null && request.getOrderId()!=0) {
                caseId=  cmMasterDetailsRepository.getCmMasterIdByOrderId(request.getOrderId());
            }
            //create the response
            response = new GetCaseIdByOrderIdServiceResponse(caseId);
            response.setMessage("Retrieved caseId for given orderId");
            response.SUCCESSFUL = true;
            log.debug("Retrieved caseId for given orderId");

        } catch (Exception e) {
            //create the response
            response = new GetCaseIdByOrderIdServiceResponse();
            response.SUCCESSFUL = false;
            log.error("Failed to retrieve caseId for given orderId");
            response.setMessage("Failed to retrieve caseId for given orderId");
        }
        return response;
    }
}