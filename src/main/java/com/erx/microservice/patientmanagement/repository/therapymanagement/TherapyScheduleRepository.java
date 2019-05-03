package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyInstance;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TherapyScheduleRepository extends JpaRepository<TherapySchedule,Long> {
    @Transactional
    @Modifying
    @Query("update TherapySchedule TS set TS.checkInTime=?2 where TS.id = ?1  ")
    void updateTherapyCheckInTimeById(Long TherapyInstanceId, LocalDateTime checkInTime);

    @Transactional
    @Modifying
    @Query("update TherapySchedule TS set TS.checkOutTime=?2 where TS.id= ?1  ")
    void updateTherapyCheckOutTimeById(Long TherapyInstanceId, LocalDateTime checkOutTime);


    @Query("select TS.id from TherapySchedule TS Join TS.therapyInstance TI Join TI.therapyPlanning TP " +
            "where TP.cmMaster.id=?1 and TI.recordStatus = 1")
    List<Long> findTherapyScheduleIdsByCaseId(Long caseId);
}
