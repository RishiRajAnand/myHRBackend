package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname;


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

@Service("getReferralPatientMasterByReferralNameService")
public class GetReferralPatientMasterByReferralNameServiceImpl implements GetReferralPatientMasterByReferralNameService {

    private final Logger log = LoggerFactory.getLogger(GetReferralPatientMasterByReferralNameServiceImpl.class);

    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Override
    public GetReferralPatientMasterByReferralNameServiceResponse execute(GetReferralPatientMasterByReferralNameServiceRequest request) throws Exception {

        GetReferralPatientMasterByReferralNameServiceResponse response = null;
        String referralName = null;
        Long clinicLocationId = null;
        List<ReferralPatientMaster> referralPatientMasters = null;
        List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs = null;
        try {
            log.debug("call to retrieve ReferralPatientMaster");
            //retrieve referralName from request
            referralName = request.getReferralName();
            //retrieve clinicLocationId from request
            clinicLocationId = request.getClinicLocationId();
            //finding ReferralPatientMaster as List
            referralPatientMasters = referralPatientMasterRepository.findByReferralName(clinicLocationId, referralName);
            //Creating leadTracketReferralPatientMasterDTOs
            leadTracketReferralPatientMasterDTOs = new ArrayList<LeadTracketReferralPatientMasterDTO>();
            //setting leadTracketReferralPatientMasterDTOs
            for (ReferralPatientMaster referralPatientMaster : referralPatientMasters) {
                //setting LeadTracketReferralPatientMasterDTO Value
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
                //add leadTracketReferralPatientMasterDTO to LeadTracketReferralPatientMasterDTOs
                leadTracketReferralPatientMasterDTOs.add(leadTracketReferralPatientMasterDTO);
            }
            //create response
            response = new GetReferralPatientMasterByReferralNameServiceResponse(leadTracketReferralPatientMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  retrieved successfully for " + referralName + " referralName");
            log.debug("ReferralPatientMaster is  retrieved successfully for " + referralName + " referralName");

        } catch (Exception e) {
            //create response
            response = new GetReferralPatientMasterByReferralNameServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not retrieved successfully for given referralName");
            log.error("ReferralPatientMaster is not retrieved successfully for given referralName");
        }
        return response;
    }


}
