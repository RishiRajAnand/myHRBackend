package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 06-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMasterMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyMasterMedicineRepository extends JpaRepository<TherapyMasterMedicine,Long>{

    @Query("select tmm from TherapyMasterMedicine tmm where tmm.therapyMaster.id=?1 and tmm.recordStatus = 1")
    List<TherapyMasterMedicine> findByTherapyMasterId(Long therapyMasterId);

    @Query("select tmm.productCatalogueCommonDetailId,tmm.medicineTypeId,tmm.quantity,tmm.uomMasterId," +
            "tmm.medicineInstructions from TherapyMasterMedicine tmm where tmm.therapyMaster.id=?1 and tmm.recordStatus = 1")
    List<Object[]> findTherapyMasterMedicineDetailsByTherapyMasterId(Long therapyMasterId);
}
