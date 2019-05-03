package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype;

/*
* created by Brighty on 11-01-18
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

@Service("searchPatientRefundByCodeOrTypeService")
public class SearchPatientRefundByCodeOrTypeServiceImpl implements SearchPatientRefundByCodeOrTypeService {

    private final Logger log = LoggerFactory.getLogger(SearchPatientRefundByCodeOrTypeServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public SearchPatientRefundByCodeOrTypeServiceResponse execute(SearchPatientRefundByCodeOrTypeServiceRequest request) throws ServiceException {

        SearchPatientRefundByCodeOrTypeServiceResponse response = null;
        List<PatientRefund> patientRefunds = null;
        List<PatientRefundDetailDTO> patientRefundDetailDTOs = new ArrayList<>();
        UserStaff userStaff = null;
        try {
            log.debug("Call to search Patient Refund By refundNumber or Refund Type");
            if (request.getPatientId() != null & request.getSearchValue() != null) {
                //retrieve the refunds
                patientRefunds = patientRefundRepository.findByRefundNumberOrRefundType(request.getPatientId(), request.getSearchValue());
                //Set the DTO list
                for (PatientRefund refund : patientRefunds) {
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
                    patientRefundDetailDTO.setRefundedDate(refund.getRefundDate().toLocalDate());
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
                    //add to list
                    patientRefundDetailDTOs.add(patientRefundDetailDTO);
                }
                if (patientRefundDetailDTOs.size() == 0) {
                    response = new SearchPatientRefundByCodeOrTypeServiceResponse(patientRefundDetailDTOs);
                    response.SUCCESSFUL = true;
                    response.setMessage("No Refund details found");
                    log.debug("No Refund details found for the patient with id : " + request.getPatientId());
                    return response;
                }
            }
            //create response
            response = new SearchPatientRefundByCodeOrTypeServiceResponse(patientRefundDetailDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved " + patientRefunds.size() + " Refund details Successfully");
            log.debug("Retrieved " + patientRefunds.size() + " Refund details Successfully");
        } catch (Exception e) {
            response = new SearchPatientRefundByCodeOrTypeServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund details");
            log.error("Failed to retrieve Refund details" + e.getStackTrace());
        }
        return response;
    }
}
