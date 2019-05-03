package com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMedicineTreatmentResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getTherapyTreatmentByCaseIdService")
public class GetTherapyTreatmentByCaseIdServiceImpl implements GetTherapyTreatmentByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyTreatmentByCaseIdServiceImpl.class);


    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetTherapyTreatmentByCaseIdServiceResponse execute(GetTherapyTreatmentByCaseIdServiceRequest request) throws ServiceException {
        GetTherapyTreatmentByCaseIdServiceResponse response = null;
        List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs = new ArrayList<>();
        try {
            log.debug("Call to get therapy treatment by case id");
            cmTherapyTreatmentResponseDTOs = getCompleteCaseDetails.getTreatmentTherapies(request.getCaseId());
            // setting the dto to response
            response = new GetTherapyTreatmentByCaseIdServiceResponse(cmTherapyTreatmentResponseDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm therapy treatment by id Successfully");
            log.debug("Retrieved cm therapy treatment by id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm therapy treatment by id");
            log.error("Failed to retrieve cm therapy treatment by id");
        }
        return response;
    }
}
