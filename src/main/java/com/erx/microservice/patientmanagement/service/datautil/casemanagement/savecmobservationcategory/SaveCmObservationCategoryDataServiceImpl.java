package com.erx.microservice.patientmanagement.service.datautil.casemanagement.savecmobservationcategory;

/*
 * created by Latha on 20-08-2018
 * */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategory;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationData;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationCategoryDataRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationCategoryRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmObservationDataRepository;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("saveCmObservationCategoryDataService")
public class SaveCmObservationCategoryDataServiceImpl implements SaveCmObservationCategoryDataService{

    private final org.slf4j.Logger log = LoggerFactory.getLogger(GetCompleteCaseDetailsServiceImpl.class);

    @Autowired
    private CmObservationCategoryRepository cmObservationCategoryRepository;

    @Autowired
    private CmObservationDataRepository cmObservationDataRepository;

    @Autowired
    private CmObservationCategoryDataRepository cmObservationCategoryDataRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    // method to save cm observation category data
    @Override
    public CmObservationCategoryData saveCmObservationData(Long lookupValueId, String dataName, Long clinicId) {
        CmObservationCategoryData cmObservationCategoryData = null;
        CmObservationCategoryData savedCmObservationCategoryData = new CmObservationCategoryData();
        CmObservationCategory observationCategory = new CmObservationCategory();
        CmObservationData observationData = new CmObservationData();
        Clinic clinic = new Clinic();
        try {
            log.debug("call to save cm observation category data");
            clinic = clinicRepository.findOne(clinicId);
            observationCategory = cmObservationCategoryRepository.getCmObservationCategoryByLookupValueId(lookupValueId);
            if(dataName != null) {
                observationData = cmObservationDataRepository.getCmObservationDataByUserInput(dataName);
            }
            if(observationCategory != null && observationData != null) {
                cmObservationCategoryData = cmObservationCategoryDataRepository.findCmObservationCategoryData(observationCategory.getId(), observationData.getId(), clinicId);
            }
            if (cmObservationCategoryData != null) {
                return cmObservationCategoryData;
            } else{
                // save in db and return the saved value
                if (cmObservationCategoryData == null) {
                    if(observationData == null) {
                        observationData = new CmObservationData();
                        observationData.setDataName(dataName);
                        observationData = cmObservationDataRepository.save(observationData);
                    }
                }
                if(observationCategory != null && observationData != null) {
                    cmObservationCategoryData = new CmObservationCategoryData();
                    cmObservationCategoryData.setClinic(clinic);
                    cmObservationCategoryData.setCmObservationCategory(observationCategory);
                    cmObservationCategoryData.setCmObservationData(observationData);
                    savedCmObservationCategoryData = cmObservationCategoryDataRepository.save(cmObservationCategoryData);
                }
            }
        } catch (Exception e) {
            log.error("failed to save cm observation data" + e.getMessage());
        }
        return savedCmObservationCategoryData;
    }
}
