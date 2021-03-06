package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentGroupMedicineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTreatmentGroupMedicineDetailRepository extends JpaRepository<CmTreatmentGroupMedicineDetail,Long>{

    @Query("select cmg from CmTreatmentGroupMedicineDetail cmg where cmg.cmTreatmentMedicineList.id = ?1 and cmg.recordStatus = 1")
    List<CmTreatmentGroupMedicineDetail> findCmTreatmentGroupByMedicineDetail(Long cmTreatmentMedicineDetailId);
}
