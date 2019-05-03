package com.erx.microservice.patientmanagement.repository.therapymanagement;

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyPlanningMedicineTypeRepository extends JpaRepository<TherapyPlanningMedicineType,Long> {

    @Query("select TMT.id from TherapyPlanningMedicineType TMT where TMT.therapyPlanning.id = ?1 " +
            " and TMT.recordStatus = 1")
    List<Long> getTherapyPlanningMedicineTypeIdsByPlanningId(Long PlanningId);

    @Query("select TMT from TherapyPlanningMedicineType TMT where TMT.therapyPlanning.id=?1 and TMT.recordStatus = 1")
    List<TherapyPlanningMedicineType> findByTherapyPlanningId(Long therapyPlanningId);
}
