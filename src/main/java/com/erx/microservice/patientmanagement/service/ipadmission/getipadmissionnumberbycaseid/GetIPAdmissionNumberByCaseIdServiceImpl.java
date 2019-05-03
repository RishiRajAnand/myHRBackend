package com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid;

/*
* created by raushan on 18-10-2018
* */


import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetIPAdmissionNumberByCaseIdServiceImpl implements GetIPAdmissionNumberByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetIPAdmissionNumberByCaseIdService.class);

    @Autowired
    private IpAdmissionRepository  ipAdmissionRepository;


    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetIPAdmissionNumberByCaseIdServiceResponse execute(GetIPAdmissionNumberByCaseIdServiceRequest request) throws ServiceException {

        GetIPAdmissionNumberByCaseIdServiceResponse response = null;
        String ipAdmissionNumber = null;
        Long ipAdmissionId = null;
        try {
            log.debug("call to retrieve ipAdmissionNumber by given CaseId : " + request.getCaseId());
            if (request.getCaseId() != 0) {
                //retrieve the ipAdmissionNumber
                Long orderId= cmMasterDetailsRepository.getOrderIdByCaseId(request.getCaseId());
                if(orderId!=null) {
                    ipAdmissionId = serviceGateway.getIpAdmissionIdByOrderId(orderId);
                    if (ipAdmissionId != null) {
                        ipAdmissionNumber = ipAdmissionRepository.findIpAdmissionNumberById(ipAdmissionId);
                    }
                }
            }
            //create response
            response = new GetIPAdmissionNumberByCaseIdServiceResponse(ipAdmissionNumber);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved ipAdmissionNumber by given CaseId  successfully");
            log.debug("Retrieved ipAdmissionNumber by given CaseId  successfully");
        } catch (Exception e) {
            //create response
            response = new GetIPAdmissionNumberByCaseIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved ipAdmissionNumber by given CaseId  successfully");
            log.error("Not retrieved ipAdmissionNumber by given CaseId  successfully");
        }
        return response;
    }
}
