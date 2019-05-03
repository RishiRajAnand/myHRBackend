package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralid;


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

@Service("getReferralPatientMasterByReferralIdService")
public class GetReferralPatientMasterByReferralIdServiceImpl implements GetReferralPatientMasterByReferralIdService {


    private final Logger log = LoggerFactory.getLogger(GetReferralPatientMasterByReferralIdServiceImpl.class);
    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Override
    public GetReferralPatientMasterByReferralIdServiceResponse execute(GetReferralPatientMasterByReferralIdServiceRequest request) throws Exception {

        GetReferralPatientMasterByReferralIdServiceResponse response = null;
        String referralId =null;
        Long clinicLocationId =null;
        List<ReferralPatientMaster> referralPatientMasters =null;
        List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs =null;
        try {
            log.debug("call to retrieve ReferralPatientMaster");
            //retrieve referralId from request
             referralId = request.getReferralId();
            //retrieve clinicLocationId from request
             clinicLocationId = request.getClinicLocationId();
            //finding ReferralPatientMaster as List
             referralPatientMasters = referralPatientMasterRepository.findByReferralId(clinicLocationId, referralId);
            //crating List of LeadTracketReferralPatientMasterDTO
            leadTracketReferralPatientMasterDTOs = new ArrayList<LeadTracketReferralPatientMasterDTO>();
            //Setting leadTracketReferralPatientMasterDTOs Values
            for (ReferralPatientMaster referralPatientMaster : referralPatientMasters) {
                //setting LeadTracketReferralPatientMasterDTO value
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
                //adding leadTracketReferralPatientMasterDTO to leadTracketReferralPatientMasterDTOs
                leadTracketReferralPatientMasterDTOs.add(leadTracketReferralPatientMasterDTO);
            }

            //create response
            response = new GetReferralPatientMasterByReferralIdServiceResponse(leadTracketReferralPatientMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  retrieved successfully of " + referralId + " referralId");
            log.debug("ReferralPatientMaster is  retrieved successfully of  " + referralId + "  referralId");

        } catch (Exception e) {
            //create response
            response = new GetReferralPatientMasterByReferralIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not retrieved successfully of given referralId");
            log.error("ReferralPatientMaster is not retrieved successfully of given referralId");
        }
        return response;
    }


}
