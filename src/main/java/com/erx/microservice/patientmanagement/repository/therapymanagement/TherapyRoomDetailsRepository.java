package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyRoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyRoomDetailsRepository extends JpaRepository<TherapyRoomDetails, Long>{

    @Query("select trd from TherapyRoomDetails trd join trd.therapyRoomMaster trm " +
            "join trd.therapyRoomType trt where trm.clinicLocation.id=?1 and trt.clinicLocation.id=?1 and trd.recordStatus = 1")
    List<TherapyRoomDetails> findRoomByClinicLocationId(Long clinicLocationId);

    @Query("select trm.name from TherapyRoomDetails trd join trd.therapyRoomMaster trm " +
            " where trd.id=?1 and trd.recordStatus = 1")
    String findRoomNameByTherapyRoomDetailId(Long therapyRoomDetailId);
}
