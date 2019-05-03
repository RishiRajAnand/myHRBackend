package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMedicineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmMedicineGroupRepository extends JpaRepository<CmMedicineGroup,Long>{


    @Query("select cmg from CmMedicineGroup cmg where cmg.groupMedicineMaster.id=?1 and cmg.recordStatus = 1")
    List<CmMedicineGroup> findCmMedicineByGroupMedicineId(Long cmGroupMedicineId);
}
