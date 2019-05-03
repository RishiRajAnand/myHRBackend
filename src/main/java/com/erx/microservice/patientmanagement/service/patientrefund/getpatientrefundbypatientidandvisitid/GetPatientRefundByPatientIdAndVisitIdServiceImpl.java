package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid;

/*
* created by Raushan on 06-02-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientRefund;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientRefundRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundMinimalDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientRefundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getPatientRefundByPatientIdAndVisitIdServiceImpl")
public class GetPatientRefundByPatientIdAndVisitIdServiceImpl implements GetPatientRefundByPatientIdAndVisitIdService {

    private final Logger log = LoggerFactory.getLogger(GetPatientRefundByPatientIdAndVisitIdServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;

    @Override
    public GetPatientRefundByPatientIdAndVisitIdServiceResponse execute(GetPatientRefundByPatientIdAndVisitIdServiceRequest request) throws ServiceException {

        GetPatientRefundByPatientIdAndVisitIdServiceResponse response = null;
        List<PatientRefund> patientRefunds = null;
        List<PatientRefundMinimalDTO> patientRefundMinimalDTOs = new ArrayList<>();
        try {
            log.debug("Call to search Patient Refund By patientId and visitId ");
            if (request.getPatientId() != null & request.getVisitId() != null) {
                //retrieve the refunds
                patientRefunds = patientRefundRepository.findPatientRefundByPatientIdAndVisitId(request.getPatientId(), request.getVisitId());
                //Set the DTO list
                for (PatientRefund patientRefund : patientRefunds) {
                    PatientRefundMinimalDTO patientRefundMinimalDTO = new PatientRefundMinimalDTO();
                    patientRefundMinimalDTO.setId(patientRefund.getId());
                    patientRefundMinimalDTO.setRefundTypeName(patientRefund.getRefundType().getName());
                    patientRefundMinimalDTO.setRefundedDate(patientRefund.getRefundDate().toLocalDate());
                    patientRefundMinimalDTO.setRefundableAmount(patientRefund.getRefundableAmount());
                    patientRefundMinimalDTO.setRefundNumber(patientRefund.getRefundNumber());
                    patientRefundMinimalDTOs.add(patientRefundMinimalDTO);
                }

            }
            //create response
            response = new GetPatientRefundByPatientIdAndVisitIdServiceResponse(patientRefundMinimalDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Refund details Successfully");
            log.debug("Retrieved  Refund details Successfully");
        } catch (Exception e) {
            response = new GetPatientRefundByPatientIdAndVisitIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund details");
            log.error("GetPatientRefundByPatientIdAndVisitIdServiceImpl---->execute()--->" + e.getStackTrace());
        }
        return response;
    }
}
