package com.erx.microservice.patientmanagement.repository.therapymanagement;

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TherapyInstanceRepository extends JpaRepository<TherapyInstance,Long> {

    @Query("select TI from TherapyInstance TI where TI.therapyPlanning.id=?1 and TI.recordStatus = 1")
    List<TherapyInstance> findByTherapyPlanningId(Long therapyPlanningId);

    @Query("select TI.id from TherapyInstance TI where TI.therapyPlanning.id=?1 and TI.recordStatus = 1")
    List<Long> findTherapyInstanceIdsByTherapyPlanningId(Long therapyPlanningId);

}
