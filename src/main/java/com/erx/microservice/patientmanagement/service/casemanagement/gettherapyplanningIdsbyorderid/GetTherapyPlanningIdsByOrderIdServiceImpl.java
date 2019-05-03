package com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid;

/*
* created by Raushan on 25-09-2018
* */

import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GetTherapyPlanningIdsByOrderIdServiceImpl implements GetTherapyPlanningIdsByOrderIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyPlanningIdsByOrderIdServiceImpl.class);

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Override
    public GetTherapyPlanningIdsByOrderIdServiceResponse execute(GetTherapyPlanningIdsByOrderIdServiceRequest  request) throws ServiceException {
        Long orderId=null;
        GetTherapyPlanningIdsByOrderIdServiceResponse response = null;
        Set<Long> therapyPlanningIds=null;
        try {
            log.debug("Call to get TherapyPlanning Ids");
            // retrieve acd master
            orderId=request.getOrderId();
            therapyPlanningIds=therapyPlanningRepository.getTherapyPlanningIdsByOrderId(orderId);
            // setting the dto to response
            response = new GetTherapyPlanningIdsByOrderIdServiceResponse(therapyPlanningIds);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved TherapyPlanning Ids Successfully for given orderId ="+orderId);
            log.debug("Retrieved TherapyPlanning Ids Successfully for given orderId ="+orderId);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve TherapyPlanning Ids for given orderId");
            log.error("Failed to retrieve TherapyPlanning Ids for given orderId");
        }
        return response;
    }
}
