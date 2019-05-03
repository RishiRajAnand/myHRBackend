package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMasterRoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyMasterRoomDetailRepository extends JpaRepository<TherapyMasterRoomDetail,Long>{

    @Query("select tmr from TherapyMasterRoomDetail tmr where tmr.therapyMaster.id=?1 and tmr.recordStatus = 1")
    List<TherapyMasterRoomDetail> findByTherapyMasterId(Long therapyMasterId);
}
