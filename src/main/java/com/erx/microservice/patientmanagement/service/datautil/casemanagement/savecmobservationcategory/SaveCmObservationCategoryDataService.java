package com.erx.microservice.patientmanagement.service.datautil.casemanagement.savecmobservationcategory;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;

public interface SaveCmObservationCategoryDataService {
    CmObservationCategoryData saveCmObservationData(Long lookupValueId, String dataName, Long clinicId);
}
