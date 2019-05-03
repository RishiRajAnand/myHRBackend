package com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatment;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentGroupMedicineDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentMedicineDetail;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentGroupMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTreatmentRepository;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getMedicineTreatmentByCaseIdService")
public class GetMedicineTreatmentByCaseIdServiceImpl implements GetMedicineTreatmentByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetMedicineTreatmentByCaseIdServiceImpl.class);


    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Override
    public GetMedicineTreatmentByCaseIdServiceResponse execute(GetMedicineTreatmentByCaseIdServiceRequest request) throws ServiceException {
        GetMedicineTreatmentByCaseIdServiceResponse response = null;
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;
        try {
            log.debug("Call to get medicine treatment by case id");
            cmMedicineTreatmentResponseDTOs = getCompleteCaseDetails.getTreatmentMedicines(request.getCaseId());
            // setting the dto to response
            response = new GetMedicineTreatmentByCaseIdServiceResponse(cmMedicineTreatmentResponseDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved cm medicine treatment by id Successfully");
            log.debug("Retrieved cm medicine treatment by id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm medicine treatment by id");
            log.error("Failed to retrieve cm medicine treatment by id");
        }
        return response;
    }
}
