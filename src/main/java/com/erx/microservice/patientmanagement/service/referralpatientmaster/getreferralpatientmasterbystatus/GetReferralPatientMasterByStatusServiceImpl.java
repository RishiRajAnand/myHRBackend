package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbystatus;


import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import com.erx.microservice.patientmanagement.repository.ReferralPatientMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.LeadTracketReferralPatientMasterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* created by Raushan on 20-11-2017
* */

@Service("getReferralPatientMasterByStatusService")
public class GetReferralPatientMasterByStatusServiceImpl implements GetReferralPatientMasterByStatusService {

    private final Logger log = LoggerFactory.getLogger(GetReferralPatientMasterByStatusServiceImpl.class);

    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Override
    public GetReferralPatientMasterByStatusServiceResponse execute(GetReferralPatientMasterByStatusServiceRequest request) throws Exception {

        GetReferralPatientMasterByStatusServiceResponse response = null;
        Long clinicLocationId =null;
        String status =null;
        List<ReferralPatientMaster> referralPatientMasters =null;
        List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs = null;
        try {
            log.debug("call to retrieve ReferralPatientMaster");
            //retrieve status from request
             status = request.getStatus();
            //retrieve clinicLocationId from request
             clinicLocationId = request.getClinicLocationId();
            //finding ReferralPatientMaster as List
            referralPatientMasters = referralPatientMasterRepository.findByStatus(clinicLocationId, status);
            //crating List of LeadTracketReferralPatientMasterDTO
            leadTracketReferralPatientMasterDTOs = new ArrayList<LeadTracketReferralPatientMasterDTO>();
            //Setting leadTracketReferralPatientMasterDTOs Values
            for (ReferralPatientMaster referralPatientMaster : referralPatientMasters) {
                //Setting LeadTracketReferralPatientMasterDTO Values
                LeadTracketReferralPatientMasterDTO leadTracketReferralPatientMasterDTO = new LeadTracketReferralPatientMasterDTO();
                leadTracketReferralPatientMasterDTO.setId(referralPatientMaster.getId());
                leadTracketReferralPatientMasterDTO.setReferralId(referralPatientMaster.getReferralId());
                leadTracketReferralPatientMasterDTO.setReferralName(referralPatientMaster.getReferralName());
                leadTracketReferralPatientMasterDTO.setReferralRate(referralPatientMaster.getReferralRate());
                leadTracketReferralPatientMasterDTO.setStatus(referralPatientMaster.getStatus());
                leadTracketReferralPatientMasterDTO.setReferralType(referralPatientMaster.getReferralTypeLookupValue().getName());
                leadTracketReferralPatientMasterDTO.setReferralRateType(referralPatientMaster.getReferralRateLookupValue().getName());
                if(referralPatientMaster.getUserDetail()!=null) {
                    String doctorFirstName = referralPatientMaster.getUserDetail().getUserStaff().getFirstName();
                    String doctorLastName = referralPatientMaster.getUserDetail().getUserStaff().getLastName();
                    String doctorName = doctorFirstName + " " + doctorLastName;
                    leadTracketReferralPatientMasterDTO.setDoctorName(doctorName);
                }
                //addding leadTracketReferralPatientMasterDTO to leadTracketReferralPatientMasterDTOs
                leadTracketReferralPatientMasterDTOs.add(leadTracketReferralPatientMasterDTO);
            }
            //create response
            response = new GetReferralPatientMasterByStatusServiceResponse(leadTracketReferralPatientMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  retrieved successfully for " + status + " status");
            log.debug("ReferralPatientMaster is  retrieved successfully for " + status + " status");

        } catch (Exception e) {
            //create response
            response = new GetReferralPatientMasterByStatusServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not retrieved successfully for given status");
            log.error("ReferralPatientMaster is not retrieved successfully for given status");
        }
        return response;
    }


}
