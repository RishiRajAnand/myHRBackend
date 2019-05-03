package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmObservationCategoryDataRepository extends JpaRepository<CmObservationCategoryData,Long> {

    @Query("select ccd from CmObservationCategoryData ccd where ccd.clinic.id = ?1 and ccd.cmObservationCategory.id = ?2 and ccd.recordStatus = 1")
    List<CmObservationCategoryData> getObservationListByClinicCategory(Long clinicId, Long cmObservationCategoryId);

    @Query("select ccd from CmObservationCategoryData ccd where ccd.cmObservationCategory.id = ?1 and ccd.cmObservationData.id = ?2 and ccd.clinic.id = ?3 and  ccd.recordStatus = 1")
    CmObservationCategoryData findCmObservationCategoryData(Long cmObservationCategoryId, Long cmObservationDataId, Long clinicId);
}
