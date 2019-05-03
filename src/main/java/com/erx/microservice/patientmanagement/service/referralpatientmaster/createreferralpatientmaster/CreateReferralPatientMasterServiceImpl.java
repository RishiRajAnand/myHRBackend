package com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster;


import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.ReferralPatientMasterRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.CreateReferralPatientMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.ReferralPatientMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.ReferralPatientMasterMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Raushan on 20-11-2017
* */

@Service("createReferralPatientMaster")
public class CreateReferralPatientMasterServiceImpl implements CreateReferralPatientMasterService {

    private final Logger log = LoggerFactory.getLogger(CreateReferralPatientMasterServiceImpl.class);

    @Autowired
    private ReferralPatientMasterRepository referralPatientMasterRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private LookupValueRepository lookupValueRepository;
    @Autowired
    private ClinicLocationRepository clinicLocationRepository;
    @Autowired
    private ReferralPatientMasterMapper referralPatientMasterMapper;
    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public CreateReferralPatientMasterServiceResponse execute(CreateReferralPatientMasterServiceRequest request) throws Exception {

        CreateReferralPatientMasterServiceResponse response = null;
        Long clinicLocationId = null;
        Long referralExternalLookupValueId = null;
        Long userDetailId = null;
        Long referralRateLookupValueId = null;
        Long referralTypeLookupValueId = null;
        CreateReferralPatientMasterDTO createReferralPatientMasterDTO = null;
        ClinicLocation foundClinicLocation = null;

        try {
            log.debug("call to save ReferralPatientMaster");
            //retrieve the ReferralPatientMasterDTO and all Foreign Id object from request
            clinicLocationId = request.getCreateReferralPatientMasterDTO().getClinicLocationId();
            referralExternalLookupValueId = request.getCreateReferralPatientMasterDTO().getReferralExternalLookupValueId();
            userDetailId = request.getCreateReferralPatientMasterDTO().getUserDetailId();
            referralRateLookupValueId = request.getCreateReferralPatientMasterDTO().getReferralRateLookupValueId();
            referralTypeLookupValueId = request.getCreateReferralPatientMasterDTO().getReferralTypeLookupValueId();
            createReferralPatientMasterDTO = request.getCreateReferralPatientMasterDTO();
            //Converting DTO to domain
            ReferralPatientMaster referralPatientMaster = referralPatientMasterMapper.createReferralPatientMasterDTOTOReferralPatientMaster(createReferralPatientMasterDTO);
            //Finding and setting ClinicLocation to referralPatientMaster
            if (clinicLocationId != 0) {
                boolean foundClinicLocationId = clinicLocationRepository.exists(clinicLocationId);
                if (foundClinicLocationId) {
                    foundClinicLocation = clinicLocationRepository.findOne(clinicLocationId);
                    //referralPatientMaster.setClinicLocation(foundClinicLocation);
                }
            }
            //Finding and setting LookupValue to referralPatientMaster
            if (referralExternalLookupValueId != 0) {
                boolean foundReferralExternalLookupValueId = lookupValueRepository.exists(referralExternalLookupValueId);
                if (foundReferralExternalLookupValueId) {
                    LookupValue foundReferralExternalLookupValue = lookupValueRepository.findOne(referralExternalLookupValueId);
                    referralPatientMaster.setReferralExternalLookupValue(foundReferralExternalLookupValue);
                }
            }

            //Finding and setting LookupValue to referralPatientMaster
            if (referralRateLookupValueId != 0) {
                boolean foundReferralRateLookupValueId = lookupValueRepository.exists(referralRateLookupValueId);
                if (foundReferralRateLookupValueId) {
                    LookupValue foundReferralRateLookupValue = lookupValueRepository.findOne(referralRateLookupValueId);
                    referralPatientMaster.setReferralRateLookupValue(foundReferralRateLookupValue);
                }
            }
            //Finding and setting LookupValue to referralPatientMaster
            if (referralTypeLookupValueId != 0) {
                boolean foundReferralTypeLookupValueId = lookupValueRepository.exists(referralTypeLookupValueId);
                if (foundReferralTypeLookupValueId) {
                    LookupValue foundReferralTypeLookupValue = lookupValueRepository.findOne(referralTypeLookupValueId);
                    referralPatientMaster.setReferralTypeLookupValue(foundReferralTypeLookupValue);
                }
            }
            //Finding and setting UserDetail to referralPatientMaster
            if (userDetailId != 0) {
                boolean foundUserDetailId = userDetailRepository.exists(userDetailId);
                if (foundUserDetailId) {
                    UserDetail foundUserDetail = userDetailRepository.findOne(userDetailId);
                    referralPatientMaster.setUserDetail(foundUserDetail);

                }
            }
            //generate uniqueID
            if (referralPatientMaster.getId() == null) {
                Long clinicId = foundClinicLocation.getClinic().getId();
                //generate unique id
                GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                generateUniqueIDDTO.setCurrentTableName(ErxConstants.BTM_CURRENT_TABLE_NAME);
                generateUniqueIDDTO.setCurrentColumnName(ErxConstants.BTM_CURRENT_COLUMN_NAME);
                generateUniqueIDClinicDTO.setId(clinicId);
                generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                //call generateUniqueID
                String referralPatientGeneratedCode = serviceGateway.generateUniqueID(generateUniqueIDDTO);
                if (referralPatientGeneratedCode != null) {
                    //save the unique Id generated
                    referralPatientMaster.setReferralId(referralPatientGeneratedCode);
                } else {
                    response.SUCCESSFUL = false;
                    response.setMessage(" uniqueId not generated for ReferralPatientMaster ");
                    return response;
                }
            }
            //saving ReferralPatientMaster
            ReferralPatientMaster savedReferralPatientMaster = referralPatientMasterRepository.save(referralPatientMaster);
            //Converting  domain to DTO
            ReferralPatientMasterDTO foundReferralPatientMasterDTO = referralPatientMasterMapper.referralPatientMasterTOReferralPatientMasterDTO(referralPatientMaster);
            //create response
            response = new CreateReferralPatientMasterServiceResponse(foundReferralPatientMasterDTO);
            response.SUCCESSFUL = true;
            response.setMessage("saved ReferralPatientMaster successfully with id " + savedReferralPatientMaster.getId());
            log.debug("saved ReferralPatientMaster successfully with id " + savedReferralPatientMaster.getId());


        } catch (Exception e) {
            //create response
            response = new CreateReferralPatientMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not saved ReferralPatientMaster successfully");
            log.error("Not saved ReferralPatientMaster successfully");
        }
        return response;
    }


}
