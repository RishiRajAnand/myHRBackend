package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 06-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMasterMedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyMasterMedicineTypeRepository extends JpaRepository<TherapyMasterMedicineType,Long>{

    @Query("select tmmt from TherapyMasterMedicineType tmmt where tmmt.therapyMaster.id=?1 and tmmt.recordStatus = 1")
    List<TherapyMasterMedicineType> findByTherapyMasterId(Long therapyMasterId);

    @Query("select tmt.medicineTypeId from TherapyMasterMedicineType tmt where tmt.therapyMaster.id=?1 and tmt.recordStatus = 1")
    List<Long> findMedicineTypeIdsByTherapyMasterId(Long therapyMasterId);
}
