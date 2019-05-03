package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatmentMedicineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTreatmentMedicineDetailRepository extends JpaRepository<CmTreatmentMedicineDetail,Long>{

    @Query("select cmtd from CmTreatmentMedicineDetail cmtd where cmtd.cmTreatment.id = ?1 and cmtd.recordStatus = 1")
    List<CmTreatmentMedicineDetail> findCmTreatmentMedicineByCmTreatment(Long cmTreatmentId);
}
