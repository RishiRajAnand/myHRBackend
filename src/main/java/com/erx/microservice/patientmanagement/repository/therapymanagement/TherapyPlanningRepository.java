package com.erx.microservice.patientmanagement.repository.therapymanagement;

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TherapyPlanningRepository extends JpaRepository<TherapyPlanning,Long> {

    @Query("select tp from TherapyPlanning tp where tp.cmMaster.id=?1 and tp.serviceCatalogueId=?2 and tp.recordStatus = 1")
    List<TherapyPlanning> getTherapyPlanningByCaseAndService(Long cmMasterId, Long serviceCatalogueId);

    @Query("select tp from TherapyPlanning tp where tp.cmMaster.id=?1 and tp.recordStatus = 1 and tp.therapyStartDate IS NOT NULL")
    List<TherapyPlanning> getPlanningByStart(Long cmMasterId);

    @Query("select tp from TherapyPlanning tp where tp.cmMaster.id=?1 and tp.recordStatus = 1")
    List<TherapyPlanning> getPlanningDetailsByCase(Long caseId);


    @Query("select tp.id from TherapyPlanning tp join tp.cmMaster cm join  cm.cmMasterDetailedList cmd " +
            "where cmd.bmOrderId = ?1 and cmd.recordStatus = 1")
    Set<Long> getTherapyPlanningIdsByOrderId(Long orderId);


	@Query("select tp from TherapyPlanning tp join tp.cmMaster cm where tp.updatedOn >= cm.caseCreatedDate and cm.id=?1 and tp.recordStatus = 1")
    List<TherapyPlanning> getAllTherapyPlannedForCaseOnAdmission(Long caseId);

    @Query("select tp from TherapyPlanning tp join tp.cmMaster cm where tp.updatedOn >= cm.caseCompletedDate and cm.id=?1 and tp.recordStatus = 1")
    List<TherapyPlanning> getAllTherapyPlannedForCaseOnDischarge(Long caseId);

    @Query("select tp from TherapyPlanning tp where tp.cmMasterDetails.id=?1 and tp.recordStatus = 1")
    List<TherapyPlanning> getPlanningDetailsByCmMasterDetailId(Long latestId);

    @Query("select tp.id,tp.periodicInterval from TherapyPlanning tp where tp.cmMaster.id=?1 and tp.recordStatus = 1")
    List<Object[]> getTherapyPlanningIdsAndPeriodicIntervalByCaseId(Long caseId);

    @Transactional
    @Modifying
    @Query("update TherapyPlanning TP set TP.therapyScheduleDate=?2 , TP.therapyStartDate=?3 , TP.therapyEndDate=?4 " +
            "where TP.id = ?1  ")
    void updateTherapyPlanningDetail(Long therapyPlanningId, LocalDateTime therapyScheduleDate,
                                      LocalDateTime therapyStartDate,LocalDateTime therapyEndDate);
}
