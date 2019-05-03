package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid;
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

@Service("getRefundByIdService")
public class GetPatientRefundByIdServiceImpl implements GetPatientRefundByIdService {

    private final Logger log = LoggerFactory.getLogger(GetPatientRefundByIdServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public GetPatientRefundByIdServiceResponse execute(GetPatientRefundByIdServiceRequest request) throws ServiceException {

        GetPatientRefundByIdServiceResponse response = null;
        PatientRefundDetailDTO patientRefundDetailDTO = null;
        PatientRefund refund = null;
        UserStaff userStaff = null;
        try {
            log.debug("Call to retrieve Refund");
            if (request.getRefundId() != 0) {
                //retrieve object
                refund = patientRefundRepository.findOne(request.getRefundId());
                //convert domain to DTO
                patientRefundDetailDTO = patientRefundMapper.patientRefundToPatientRefundDetailDTO(refund);
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
                    //patientRefundDetailDTO.setDepositAmount(refund.getAccountName().getDepositValue());
                }
                if (refund.getUpdatedBy() > 0) {
                    userStaff = userStaffRepository.findOne(refund.getUpdatedBy());
                    if (userStaff != null) {
                        patientRefundDetailDTO.setUserId(userStaff.getId());
                        patientRefundDetailDTO.setUserName(userStaff.getFirstName() + " " + userStaff.getLastName());
                    }
                }
            }
            //create response
            response = new GetPatientRefundByIdServiceResponse(patientRefundDetailDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Refund Successfully");
            log.debug("Retrieved Refund Successfully");
        } catch (Exception e) {
            response = new GetPatientRefundByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund");
            log.error("Failed to retrieve Refund");
        }
        return response;
    }
}
