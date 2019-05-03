package com.erx.microservice.patientmanagement.repository;
/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BedTypeMasterRepository extends JpaRepository<BedTypeMaster, Long> {
    @Query("select btm from BedTypeMaster btm where btm.clinicLocationId = ?1 and btm.recordStatus = 1")
    List<BedTypeMaster> getBedTypeMastersByClinicLocation(Long clinicLocationId);

    boolean existsByBedTypeNameAndClinicLocationIdAndIsDaycareAndRecordStatus
            (String bedTypeName, Long clinicLocationId, boolean daycare, int recordStatus);

    @Query("select count(btm)from BedTypeMaster btm where btm.sequenceOrderNo = ?1 and btm.clinicLocationId = ?2 and btm.recordStatus = 1")
    int existsBySequenceOrderNo(Long sequenceOrderNo, Long clinicLocationId);

    int countByIdInAndRecordStatus(List<Long> ids,int recordStatus);

    @Transactional
    @Modifying
    @Query("update BedTypeMaster btm set btm.sequenceOrderNo=?2 where btm.id=?1")
    void swapSequenceOrderByBedTypeId(Long bedTypeMasterId, Long fromBedTypeSequenceOrderNo);


    List<BedTypeMaster> findByClinicLocationIdAndStatusAndRecordStatusOrderBySequenceOrderNoAsc
            (Long clinicLocationId, Boolean status, int recordStatus);

    List<BedTypeMaster> findByClinicLocationIdAndRecordStatusOrderBySequenceOrderNoAsc(Long clinicLocationId, int recordStatus);


    @Query("select btm from BedTypeMaster btm where btm.clinicLocationId = ?1 and btm.isDaycare=?2 and btm.recordStatus = 1")
    List<BedTypeMaster> getBedTypeMastersByClinicLocationAndIsDaycare(long clinicLocationId, boolean isDayCare);
}
