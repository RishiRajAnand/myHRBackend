package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbydoctorname;


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

@Service("getReferralPatientMasterByDoctorNameService")
public class GetReferralPatientMasterByDoctorNameServiceImpl implements GetReferralPatientMasterByDoctorNameService {

    private final Logger log = LoggerFactory.getLogger(GetReferralPatientMasterByDoctorNameServiceImpl.class);
    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Override
    public GetReferralPatientMasterByDoctorNameServiceResponse execute(GetReferralPatientMasterByDoctorNameServiceRequest request) throws Exception {

        GetReferralPatientMasterByDoctorNameServiceResponse response = null;
        String doctorNameReq = null;
        Long clinicLocationId = null;
        List<LeadTracketReferralPatientMasterDTO> leadTracketReferralPatientMasterDTOs = null;
        List<ReferralPatientMaster> referralPatientMasters = null;
        try {
            log.debug("call to retrieve ReferralPatientMaster");
            //retrieve doctorName from request
            doctorNameReq = request.getDoctorName();
            //retrieve clinicLocationId from request
            clinicLocationId = request.getClinicLocationId();
            //finding ReferralPatientMaster as List
            referralPatientMasters = referralPatientMasterRepository.findByDoctorName(clinicLocationId, doctorNameReq);
            //crating List of LeadTracketReferralPatientMasterDTO
            leadTracketReferralPatientMasterDTOs = new ArrayList<LeadTracketReferralPatientMasterDTO>();
            //Setting leadTracketReferralPatientMasterDTOs Values
            for (ReferralPatientMaster referralPatientMaster : referralPatientMasters) {
                //setting LeadTracketReferralPatientMasterDTO values
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
            response = new GetReferralPatientMasterByDoctorNameServiceResponse(leadTracketReferralPatientMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  retrieved successfully for " + doctorNameReq + " doctorNameReq");
            log.debug("ReferralPatientMaster is  retrieved successfully for  " + doctorNameReq + "  doctorNameReq");

        } catch (Exception e) {
            //create response
            response = new GetReferralPatientMasterByDoctorNameServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not retrieved successfully for given doctorNameReq");
            log.error("ReferralPatientMaster is not retrieved successfully for given doctorNameReq");
        }
        return response;
    }


}
