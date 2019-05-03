package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.domain.PatientRefund;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientRefundRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientRefundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service("getRefundByClinicLocationService")
public class GetPatientRefundByClinicLocationServiceImpl implements GetPatientRefundByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetPatientRefundByClinicLocationServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public GetPatientRefundByClinicLocationServiceResponse execute(GetPatientRefundByClinicLocationServiceRequest request) throws ServiceException {

        GetPatientRefundByClinicLocationServiceResponse response = null;
        List<PatientRefundDetailDTO> patientRefundDetailDTOS = new ArrayList<>();
        List<PatientRefund> refunds = null;
        UserStaff userStaff = null;
        try {
            log.debug("Call to retrieve all Refunds");
            if (request.getClinicLocationId() != 0) {
                //retrieve the object
                refunds = patientRefundRepository.getRefundByClinicLocation(request.getClinicLocationId());
                //convert domain to DTO
                for (PatientRefund refund : refunds) {
                    PatientRefundDetailDTO patientRefundDetailDTO = patientRefundMapper.patientRefundToPatientRefundDetailDTO(refund);
                    //set the values
                    patientRefundDetailDTO.setClinicLocationId(refund.getClinicLocation().getId());
                    patientRefundDetailDTO.setPatientId(refund.getPatient().getId());
                    patientRefundDetailDTO.setPatientName(refund.getPatient().getPatientName());
                    patientRefundDetailDTO.setMRN(refund.getPatient().getPatientIdExternal());
                    LocalDateTime now = LocalDateTime.now();
                    int age = Period.between(refund.getPatient().getDateOfBirth()/*.toLocalDate()*/, now.toLocalDate()).getYears();
                    patientRefundDetailDTO.setAge(age);
                    patientRefundDetailDTO.setGender(refund.getPatient().getGender());
                    patientRefundDetailDTO.setMobileNumber(refund.getPatient().getMobileNumber());
                    patientRefundDetailDTO.setRefundTypeId(refund.getRefundType().getId());
                    patientRefundDetailDTO.setRefundTypeName(refund.getRefundType().getName());
                    if (refund.getAccountName() != null) {
                        patientRefundDetailDTO.setDepositAccountId(refund.getAccountName().getId());
                        patientRefundDetailDTO.setDepositAccountName(refund.getAccountName().getVisitType());
                    }
                    if (refund.getUpdatedBy() > 0) {
                        userStaff = userStaffRepository.findOne(refund.getUpdatedBy());
                        if (userStaff != null) {
                            patientRefundDetailDTO.setUserId(userStaff.getId());
                            patientRefundDetailDTO.setUserName(userStaff.getFirstName() + " " + userStaff.getLastName());
                        }
                    }

                    //add to list
                    patientRefundDetailDTOS.add(patientRefundDetailDTO);
                }
                if (patientRefundDetailDTOS.size() == 0) {
                    response = new GetPatientRefundByClinicLocationServiceResponse(patientRefundDetailDTOS);
                    response.SUCCESSFUL = true;
                    response.setMessage("No Refund found");
                    log.debug("No Refund details found for the clinicLocation with id : " + request.getClinicLocationId());
                    return response;
                }
                //create response
                response = new GetPatientRefundByClinicLocationServiceResponse(patientRefundDetailDTOS);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved all Refunds successfully");
                log.debug("Retrieved all Refunds successfully");
            }
        } catch (Exception e) {
            response = new GetPatientRefundByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refunds");
            log.error("Failed to retrieve Refunds");
        }
        return response;
    }
}
