package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmMasterRepository extends JpaRepository<CmMaster, Long> {

    @Query("select cm from CmMaster cm where cm.patient.id = ?1 " +
            "and cm.clinicLocation.id = ?2 and cm.recordStatus = 1")
    List<CmMaster> getCaseDetailsByPatient(Long patientId, Long clinicLocationId);

    @Query("select cm from CmMaster cm where cm.patient.id = ?1 " +
            "and cm.clinicLocation.id = ?2 and cm.userDetail.id = ?3 and cm.recordStatus = 1")
    List<CmMaster> getCaseCountByPatient(Long patientId, Long clinicLocationId, Long userDetailId);

    @Query("select cm from CmMaster cm where cm.clinicLocation.id = ?1 and cm.patient.id = ?2 " +
            "and cm.userDetail.id = ?3 and cm.recordStatus = 1")
    Page<CmMaster> getCaseDetailsByPatientAndDoctor(Long clinicLocationId, Long patientId, Long doctorId, Pageable pageable);

  @Query("select ci.id, ci.labOrderNumber,ci.createdDate, cid.serviceCatalogueId, " +
          "udd.id as uploadedDocumentId, udd.fileContentType, cid.investigationNotes, " +
          "ci.doctorSummary, cid.id, udd.filePath, cid.testedOn from CmMaster cm " +
          "inner join cm.userDetail ud " +
          "inner join cm.clinicLocation cl " +
          "inner join cm.cmMasterDetailedList cmd " +
          "inner join cmd.cmInvestigations ci " +
          "inner join ci.cmInvestigationDetails cid " +
          "left join cid.uploadedDocumentDetails udd " +
          " where cm.patient.id = ?1 and cm.id = ?2 and cl.id = ?3 and ud.id = ?4" +
          " order by ci.id , cid.id ")
    List<Object[]> getPatientAllInvestigationDetail(Long patientId, Long caseId, Long clinicLocationId, Long doctorId);

	@Query("select ct,ctm from CmMaster cm " +
           " inner join cm.cmMasterDetailedList cmd " +
           " inner join cmd.cmTreatments ct " +
           " inner join ct.cmTreatmentMedicineDetails ctm " +
           " where cm.id = ?1 and cm.recordStatus = 1 and cmd.recordStatus = 1 and ct.recordStatus = 1 and ctm.recordStatus = 1 " +
           " and cm.caseCreatedDate <= ct.givenDate order by ct.id ")
    List<Object[]> getMedicinesForAdmissionOn(Long caseId);

    @Query(" select cm from CmMaster cm " +
            " inner join cm.cmMasterDetailedList cmd " +
            " inner join cmd.cmTreatments ct " +
            " inner join ct.cmTreatmentMedicineDetails ctm " +
            " where cm.id = ?1 and cm.recordStatus = 1 and cmd.recordStatus = 1 and ct.recordStatus = 1 and ctm.recordStatus = 1 " +
            " and cm.caseCreatedDate <= ct.givenDate order by ct.id ")
    List<Object[]> getMedicinesForDischargeOn(Long caseId);
}
