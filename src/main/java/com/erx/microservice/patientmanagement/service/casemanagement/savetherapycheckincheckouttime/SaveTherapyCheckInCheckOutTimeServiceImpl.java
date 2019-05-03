package com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime;

/*
* created by raushan on 25-10-2018
* */


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyInstanceRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyScheduleRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTherapyCheckInCheckOutTimeServiceImpl implements SaveTherapyCheckInCheckOutTimeService {

    private final Logger log = LoggerFactory.getLogger(SaveTherapyCheckInCheckOutTimeService.class);

    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;

    @Autowired
    private TherapyScheduleRepository therapyScheduleRepository;


    @Override
    public SaveTherapyCheckInCheckOutTimeServiceResponse execute(SaveTherapyCheckInCheckOutTimeServiceRequest request) throws ServiceException {
        log.debug("Call to save Therapy CheckIn CheckOut Time");
        SaveTherapyCheckInCheckOutTimeServiceResponse response = new SaveTherapyCheckInCheckOutTimeServiceResponse();

        try {
            if (request.getType() != null && request.getId() != null && request.getCheckInCheckOutTime() != null) {
                if (request.getType().equalsIgnoreCase(Constants.THERAPY_CHECK_IN)) {
                    therapyScheduleRepository.updateTherapyCheckInTimeById(request.getId(), request.getCheckInCheckOutTime());
                } else {
                    therapyScheduleRepository.updateTherapyCheckOutTimeById(request.getId(), request.getCheckInCheckOutTime());
                }
                response.setMessage("Therapy CheckIn CheckOut Time saved successfully ");
                log.debug("Therapy CheckIn CheckOut Time saved successfully ");
            } else {
                response.setMessage("Invalid Input to save Therapy CheckIn CheckOut Time");
                log.debug("Invalid Input to save Therapy CheckIn CheckOut Time");
            }
            //create response


        } catch (Exception e) {
            response = new SaveTherapyCheckInCheckOutTimeServiceResponse();
            response.SUCCESSFUL = false;
            log.error("Failed to save Therapy CheckIn CheckOut Time " + e.getMessage());
            response.setMessage("Failed to save Therapy CheckIn CheckOut Time " + e.getMessage());
        }
        return response;
    }
}
