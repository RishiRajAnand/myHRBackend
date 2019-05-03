package com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmPersonalHistory;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmPersonalHistoryRepository;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.savecmobservationcategory.SaveCmObservationCategoryDataService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmPersonalHistoryDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service("savePersonalHistoryService")
public class SavePersonalHistoryServiceImpl implements SavePersonalHistoryService {

    private final Logger log = LoggerFactory.getLogger(SavePersonalHistoryServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private CmPersonalHistoryRepository cmPersonalHistoryRepository;

    @Autowired
    private SaveCmObservationCategoryDataService saveCmObservationCategoryDataService;


    @Override
    public SavePersonalHistoryServiceResponse execute(SavePersonalHistoryServiceRequest request) throws ServiceException {
        SavePersonalHistoryServiceResponse response = null;
        CmMaster cmMaster = new CmMaster();
        CmMasterDetails cmMasterDetails = new CmMasterDetails();
        CmPersonalHistory cmPersonalHistory = new CmPersonalHistory();
        CmObservationCategoryData appetiteObservationData = null;
        CmObservationCategoryData bladderObservationData = null;
        CmObservationCategoryData sleepObservationData = null;
        CmObservationCategoryData bowelObservationData = null;
        CmObservationCategoryData dietObservationData = null;
        CmObservationCategoryData habitObservationData = null;
        CmPersonalHistoryDTO cmPersonalHistoryDTO = new CmPersonalHistoryDTO();
        CmPersonalHistory savedCmPersonalHistory = new CmPersonalHistory();
        try {
            log.debug("Call to save case sheet personal history");
            //find cm master by id
            cmMaster = cmMasterRepository.findOne(request.getSaveCmPersonalHistoryDTO().getCmMasterId());
            /*if(cmMaster != null)
                cmMasterDetails.setCmMaster(cmMaster);
            cmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
            if(request.getSaveCmPersonalHistoryDTO().getPatientAppointmentId() != null){
            cmMasterDetails.setPatientAppointmentId(request.getSaveCmPersonalHistoryDTO().getPatientAppointmentId());
            }*/
            //retrieving cm master details
            cmMasterDetails = cmMasterDetailsRepository.findOne(request.getSaveCmPersonalHistoryDTO().getCmMasterDetailId());
            if(cmMasterDetails != null) {
                cmPersonalHistory.setCmMasterDetails(cmMasterDetails);

                // save for appetite category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getAppetiteObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getAppetiteObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getAppetiteObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        appetiteObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getAppetiteObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getAppetiteObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setAppetiteObservationData(appetiteObservationData);
                    }
                }

                // save for bladder category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getBladderObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getBladderObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getBladderObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        bladderObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getBladderObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getBladderObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setBladderObservationData(bladderObservationData);
                    }
                }
                // save for sleep category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getSleepObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getSleepObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getSleepObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        sleepObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getSleepObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getSleepObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setSleepObservationData(sleepObservationData);
                    }
                }
                // save for bowel category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getBowelObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getBowelObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getBowelObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        bowelObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getBowelObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getBowelObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setBowelObservationData(bowelObservationData);
                    }
                }
                // save for diet category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getDietObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getDietObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getDietObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        dietObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getDietObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getDietObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setDietObservationData(dietObservationData);
                    }
                }
                // save for habit category when observation data is null
                if(request.getSaveCmPersonalHistoryDTO().getHabitObservationDataDTO() != null) {
                    if (request.getSaveCmPersonalHistoryDTO().getHabitObservationDataDTO().getId() == null && request.getSaveCmPersonalHistoryDTO().getHabitObservationDataDTO().getCmObservationDataDTO().getDataName() != null) {
                        habitObservationData = saveCmObservationCategoryDataService.saveCmObservationData(request.getSaveCmPersonalHistoryDTO().getHabitObservationDataDTO().getLookupValueId(),
                                request.getSaveCmPersonalHistoryDTO().getHabitObservationDataDTO().getCmObservationDataDTO().getDataName().trim(), request.getSaveCmPersonalHistoryDTO().getClinicId());
                        cmPersonalHistory.setHabitObservationData(habitObservationData);
                    }
                }
                if(cmPersonalHistory != null)
                savedCmPersonalHistory = cmPersonalHistoryRepository.save(cmPersonalHistory);
            }
            cmPersonalHistoryDTO.setCmMasterDetailId(cmMasterDetails.getId());
            cmPersonalHistoryDTO.setCmPersonalHistoryId(savedCmPersonalHistory.getId());
            //create response
            response = new SavePersonalHistoryServiceResponse(cmPersonalHistoryDTO);
            response.setMessage("Case sheet personal history saved successfully ");
            log.debug("Case sheet personal history saved successfully ");
        } catch (Exception e) {
            response = new SavePersonalHistoryServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet personal history");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet personal history");
        }

        return response;
    }

}
