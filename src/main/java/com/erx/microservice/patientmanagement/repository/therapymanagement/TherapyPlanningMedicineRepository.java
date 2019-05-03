package com.erx.microservice.patientmanagement.repository.therapymanagement;


import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyPlanningMedicineRepository extends JpaRepository<TherapyPlanningMedicine,Long> {
    @Query("select TPM.id from TherapyPlanningMedicine TPM where TPM.therapyPlanning.id = ?1 " +
            " and TPM.recordStatus = 1")
    List<Long> getTherapyPlanningMedicineIdsByPlanningId(Long PlanningId);

    @Query("select TPM from TherapyPlanningMedicine TPM where TPM.therapyPlanning.id=?1 and TPM.recordStatus = 1")
    List<TherapyPlanningMedicine> findByTherapyPlanningId(Long therapyPlanningId);
}
