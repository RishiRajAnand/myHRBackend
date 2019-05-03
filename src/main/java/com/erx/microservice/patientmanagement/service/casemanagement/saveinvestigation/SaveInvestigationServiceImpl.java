package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation;

/*
* created by Latha on 17-08-2018
* */


import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCmInvestigationResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveInvestigationDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveInvestigationDetailDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("saveInvestigationService")
public class SaveInvestigationServiceImpl implements SaveInvestigationService {

    private final Logger log = LoggerFactory.getLogger(SaveInvestigationServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private ProvisionalDiagnosisMasterRepository provisionalDiagnosisMasterRepository;

    @Autowired
    private CmIcdMasterRepository cmIcdMasterRepository;

    @Autowired
    private CmAcdMasterRepository cmAcdMasterRepository;

    @Autowired
    private CmInvestigationRepository cmInvestigationRepository;

    @Autowired
    private CmInvestigationDetailRepository cmInvestigationDetailRepository;


    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public SaveInvestigationServiceResponse execute(SaveInvestigationServiceRequest request) throws ServiceException {
        SaveInvestigationServiceResponse response = null;
        CmInvestigation cmInvestigation = new CmInvestigation();
        CmInvestigation savedCmInvestigation = new CmInvestigation();
        List<CmInvestigationDetail> cmInvestigationDetails = new ArrayList<>();
        List<CmInvestigationDetail> savedCmInvestigationDetails = new ArrayList<>();
        SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO = new SaveCmInvestigationResponseDTO();

        try {
            log.debug("Call to save cm investigation" + request.getSaveInvestigationDTO().getCmMasterId());
            //frame cm investigation data
            cmInvestigation = frameCmInvestigation(request.getSaveInvestigationDTO());
            if(cmInvestigation != null)
                //save cm investigation
                savedCmInvestigation = cmInvestigationRepository.save(cmInvestigation);
            saveCmInvestigationResponseDTO.setCmInvestigationId(savedCmInvestigation.getId());
            if(request.getSaveInvestigationDTO().getSaveInvestigationDetailDTOs() != null && savedCmInvestigation != null){
                //frame cm investigation detail
                cmInvestigationDetails = frameCmInvestigationDetail(savedCmInvestigation,request.getSaveInvestigationDTO().getSaveInvestigationDetailDTOs(),request.getSaveInvestigationDTO().getClinicId());
                if(cmInvestigationDetails != null)
                    //save cm investigation details
                    savedCmInvestigationDetails = cmInvestigationDetailRepository.save(cmInvestigationDetails);

                if(savedCmInvestigationDetails != null){
                //setting the dto in response
                List<Long> cmInvestigationDetailIds = new ArrayList<>();
                for(CmInvestigationDetail cmInvestigationDetail : savedCmInvestigationDetails){
                    Long cmInvestigationDetailId = null;
                    cmInvestigationDetailId = cmInvestigationDetail.getId();
                    cmInvestigationDetailIds.add(cmInvestigationDetailId);
                    saveCmInvestigationResponseDTO.setCmInvestigationDetailId(cmInvestigationDetailIds);
                    }
                }
            }

            //create response
            response = new SaveInvestigationServiceResponse(saveCmInvestigationResponseDTO);
            response.setMessage("Cm Investigation saved successfully");
            log.debug("Cm Investigation saved successfully");
        } catch (Exception e) {
            response = new SaveInvestigationServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Cm Investigation");
            response.setMessage(e.getMessage() + " so,Failed to save Cm Investigation");
        }
        return response;
    }

    //method to frame cm investigation
    private CmInvestigation frameCmInvestigation(SaveInvestigationDTO saveInvestigationDTO) {
        CmMasterDetails savedCmMasterDetails = new CmMasterDetails();
        CmMaster cmMaster = new CmMaster();
        CmInvestigation cmInvestigation = new CmInvestigation();
        ProvisionalDiagnosisMaster provisionalDiagnosisMaster = new ProvisionalDiagnosisMaster();
        CmAcdMaster cmAcdMaster = new CmAcdMaster();
        CmIcdMaster cmIcdMaster = new CmIcdMaster();
        try {
            log.debug("Call to frame cm investigation");
            if (saveInvestigationDTO.getCmMasterId() != null) {
                cmMaster = cmMasterRepository.findOne(saveInvestigationDTO.getCmMasterId());
                // retrieve cm master details based on cm master id
                if(cmMaster != null)
                  /*  savedCmMasterDetails.setCmMaster(cmMaster);
                savedCmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
                if(saveInvestigationDTO.getPatientAppointmentId() != null){
                    savedCmMasterDetails.setPatientAppointmentId(saveInvestigationDTO.getPatientAppointmentId());
                }*/
                savedCmMasterDetails = cmMasterDetailsRepository.findOne(saveInvestigationDTO.getCmMasterDetailId());

                if(savedCmMasterDetails != null)
                    // setting the user entered values in cm treatment object
                    if(saveInvestigationDTO.getCmInvestigationId() == null) {
                        cmInvestigation.setCmMasterDetails(savedCmMasterDetails);
                        cmInvestigation.setCreatedDate(savedCmMasterDetails.getCaseCreatedDate());
                        cmInvestigation.setFinalDiagnosis(saveInvestigationDTO.isFinalDiagnosis());
                        cmInvestigation.setDoctorSummary(saveInvestigationDTO.getDoctorSummary());
                    //  cmInvestigation.setLabOrderNumber(); saved during order generation
                        if(saveInvestigationDTO.getProvisionalDiagnosisMasterId() != null){
                            provisionalDiagnosisMaster = provisionalDiagnosisMasterRepository.findOne(saveInvestigationDTO.getProvisionalDiagnosisMasterId());
                            if(provisionalDiagnosisMaster != null)
                            cmInvestigation.setProvisionalDiagnosisMaster(provisionalDiagnosisMaster);
                        }
                        if(saveInvestigationDTO.getAcdMasterId() != null){
                            cmAcdMaster = cmAcdMasterRepository.findOne(saveInvestigationDTO.getAcdMasterId());
                            if(cmAcdMaster != null)
                                cmInvestigation.setCmAcdMaster(cmAcdMaster);
                        }
                        if(saveInvestigationDTO.getIcdMasterId() != null){
                            cmIcdMaster = cmIcdMasterRepository.findOne(saveInvestigationDTO.getIcdMasterId());
                            if(cmIcdMaster != null)
                                cmInvestigation.setCmIcdMaster(cmIcdMaster);
                        }
                    }
            }
        } catch (Exception e) {
           log.error("Failed to frame cm investigation");
        }
        return cmInvestigation;
    }

    //method to frame cm investigation detail
    private List<CmInvestigationDetail> frameCmInvestigationDetail(CmInvestigation savedCmInvestigation,
                                                                   List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs, Long clinicId) {
        List<CmInvestigationDetail> cmInvestigationDetails = new ArrayList<>();
        try {
            log.debug("Call to frame cm investigation details");
            for(SaveInvestigationDetailDTO saveInvestigationDetailDTO : saveInvestigationDetailDTOs){
                CmInvestigationDetail cmInvestigationDetail = new CmInvestigationDetail();
                cmInvestigationDetail.setCmInvestigation(savedCmInvestigation);
                cmInvestigationDetail.setServiceCatalogueId(saveInvestigationDetailDTO.getServiceCatalogueId());
                UserDetail userDetail = userDetailRepository.findDoctorByUserId(saveInvestigationDetailDTO.getUserId(), clinicId);
                if(userDetail != null) {
                    cmInvestigationDetail.setAddedBy(userDetail.getId());//added by
                }
                cmInvestigationDetail.setAddedOn(saveInvestigationDetailDTO.getAddedOn());
                cmInvestigationDetail.setPerformedBy(saveInvestigationDetailDTO.getUserId());//performedBy
                cmInvestigationDetail.setTestedOn(saveInvestigationDetailDTO.getTestedOn());
                cmInvestigationDetail.setInvestigationNotes(saveInvestigationDetailDTO.getInvestigationNotes());
                cmInvestigationDetails.add(cmInvestigationDetail);
            }
        } catch (Exception e) {
            log.error("Failed to frame Cm investigation details");
        }
        return cmInvestigationDetails;
    }
}
