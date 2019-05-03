package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbycliniclocation;
/*
* created by Raushan on 14-02-18
* */


import com.erx.microservice.patientmanagement.domain.PatientRefund;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientRefundRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.SearchPatientRefundDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientRefundMapper;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.SearchPatientRefundServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.SearchPatientRefundServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPatientRefundByClinicLocationServiceImpl implements GetPatientRefundByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetPatientRefundByClinicLocationServiceImpl.class);

    @Autowired
    private PatientRefundRepository patientRefundRepository;

    @Autowired
    private PatientRefundMapper patientRefundMapper;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public CommonServiceResponse execute(CommonServiceRequest serviceRequest) throws ServiceException {

        SearchPatientRefundServiceResponse response = null;
        List<PatientRefund> patientRefunds = null;
        Long clinicLocationId = null;
        List<SearchPatientRefundDTO> searchPatientRefundDTOs = null;
        List<Object[]> patientObjects = null;
        try {
            log.debug("Call to retrieve Refund");
            searchPatientRefundDTOs = new ArrayList<>();
            SearchPatientRefundServiceRequest request = (SearchPatientRefundServiceRequest) serviceRequest;
            clinicLocationId = request.getPatientSearchCriteria().getClinicLocationId();
            patientRefunds = patientRefundRepository.getRefundByClinicLocation(clinicLocationId);
            //convert domain to DTO
            for (PatientRefund patientRefund : patientRefunds) {
                //set the values
                SearchPatientRefundDTO searchPatientRefundDTO = new SearchPatientRefundDTO();
                searchPatientRefundDTO.setPatientName(patientRefund.getPatient().getPatientName());
                searchPatientRefundDTO.setMobileNumber(patientRefund.getPatient().getMobileNumber());
                searchPatientRefundDTO.setPatientId(patientRefund.getPatient().getId());
                searchPatientRefundDTO.setMRN(patientRefund.getPatient().getPatientIdExternal());
                searchPatientRefundDTO.setId(patientRefund.getId());
                searchPatientRefundDTO.setRefundNumber(patientRefund.getRefundNumber());
                searchPatientRefundDTO.setRefundTypeId(patientRefund.getRefundType().getId());
                searchPatientRefundDTO.setRefundTypeName(patientRefund.getRefundType().getName());
                searchPatientRefundDTO.setRefundedDate(patientRefund.getRefundDate().toLocalDate());
                searchPatientRefundDTO.setRefundableAmount(patientRefund.getRefundableAmount());
                Long userId = patientRefund.getUpdatedBy();
                if (userId != null) {
                    UserStaff userStaff = userStaffRepository.findOne(userId);
                    String firstName = userStaff.getFirstName();
                    String lastName = userStaff.getLastName();
                    String fullName = firstName + " " + lastName;
                    searchPatientRefundDTO.setRefundedBy(fullName);
                }
                //add to list
                searchPatientRefundDTOs.add(searchPatientRefundDTO);

            }
            //create response
            response = new SearchPatientRefundServiceResponse(searchPatientRefundDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Refund Successfully");
            log.debug("Retrieved Refund Successfully");
        } catch (Exception e) {
            response = new SearchPatientRefundServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund");
            log.error("Failed to retrieve Refund");
        }
        return response;
    }
}

