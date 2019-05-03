package com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid;


import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import com.erx.microservice.patientmanagement.repository.ReferralPatientMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.ReferralPatientMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.ReferralPatientMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Raushan on 22-11-2017
* */

@Service("getReferralPatientMasterByIdService")
public class GetReferralPatientMasterByIdServiceImpl implements GetReferralPatientMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetReferralPatientMasterByIdServiceImpl.class);

    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;

    @Autowired
    private ReferralPatientMasterMapper referralPatientMasterMapper;

    @Override
    public GetReferralPatientMasterByIdServiceResponse execute(GetReferralPatientMasterByIdServiceRequest request) throws Exception {

        GetReferralPatientMasterByIdServiceResponse response = null;
        Long referralId = null;
        ReferralPatientMaster referralPatientMaster = null;
        String doctorFirstName = null;
        String doctorLastName = null;
        String doctorName = null;
        ReferralPatientMasterDTO referralPatientMasterDTO = null;
        try {
            log.debug("call to retrieve ReferralPatientMaster");
            //retrieve referralId from request
            referralId = request.getReferralId();
            //finding ReferralPatientMaster as List
            referralPatientMaster = referralPatientMasterRepository.findOne(referralId);
            if(referralPatientMaster.getUserDetail()!=null) {
                doctorFirstName = referralPatientMaster.getUserDetail().getUserStaff().getFirstName();
                doctorLastName = referralPatientMaster.getUserDetail().getUserStaff().getLastName();
                doctorName = doctorFirstName + " " + doctorLastName;
            }
            //Converting  domain to DTO
            referralPatientMasterDTO = referralPatientMasterMapper.referralPatientMasterTOReferralPatientMasterDTO(referralPatientMaster);
            //create response
            response = new GetReferralPatientMasterByIdServiceResponse(referralPatientMasterDTO, doctorName);
            response.SUCCESSFUL = true;
            response.setMessage("ReferralPatientMaster is  retrieved successfully for " + referralId + " referralId");
            log.debug("ReferralPatientMaster is  retrieved successfully for  " + referralId + "  referralId");

        } catch (Exception e) {
            //create response
            response = new GetReferralPatientMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster is not retrieved successfully for given referralId");
            log.error("ReferralPatientMaster is not retrieved successfully for given referralId");
        }
        return response;
    }
}
