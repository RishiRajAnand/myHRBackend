package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.getpatientrefundbymrn;


import com.erx.microservice.patientmanagement.domain.Patient;
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


/*
* created by Raushan on 14-02-18
* */

@Service
public class GetPatientRefundByMRNImpl implements GetPatientRefundByMRN {

    private final Logger log = LoggerFactory.getLogger(GetPatientRefundByMRNImpl.class);

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
        List<Object[]> objects = null;
        PatientRefund patientRefund = null;
        Long clinicLocationId = null;
        Long clinicId = null;
        String searchValue = null;
        List<SearchPatientRefundDTO> searchPatientRefundDTOs = null;
        Patient patient = null;
        try {
            log.debug("Call to retrieve Patient Refund");
            searchPatientRefundDTOs = new ArrayList<>();
            SearchPatientRefundServiceRequest request = (SearchPatientRefundServiceRequest) serviceRequest;
            clinicLocationId = request.getPatientSearchCriteria().getClinicLocationId();
            clinicId = request.getPatientSearchCriteria().getClinicId();
            searchValue = request.getPatientSearchCriteria().getSearchValue();
            //fetching patient and patientRefund list of Object
            objects = patientRepository.findPatientAndPatientRefundByMRNAndClinicLocation(searchValue, clinicLocationId);
            for (Object[] object : objects) {
                patient = (Patient) object[0];
                patientRefund = (PatientRefund) object[1];
                //set the values
                SearchPatientRefundDTO searchPatientRefundDTO = new SearchPatientRefundDTO();
                if (patient != null) {
                    searchPatientRefundDTO.setPatientName(patient.getPatientName());
                    searchPatientRefundDTO.setMobileNumber(patient.getMobileNumber());
                    searchPatientRefundDTO.setPatientId(patient.getId());
                    searchPatientRefundDTO.setMRN(patient.getPatientIdExternal());
                }
                if (patientRefund != null) {
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
                }
                //add to list
                searchPatientRefundDTOs.add(searchPatientRefundDTO);
            }
            //create response
            response = new SearchPatientRefundServiceResponse(searchPatientRefundDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Patient Refund Successfully");
            log.debug("Retrieved Patient Refund Successfully");
        } catch (Exception e) {
            response = new SearchPatientRefundServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient Refund");
            log.error("Failed to retrieve Patient Refund");
        }
        return response;
    }
}
