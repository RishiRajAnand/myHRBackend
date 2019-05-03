package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 16-11-2017
* */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedConfigurationMasterRepository extends JpaRepository<BedConfigurationMaster, Long> {

    @Query("select bcm from BedConfigurationMaster bcm where bcm.clinicLocationId = ?1 and bcm.recordStatus = 1 " +
            "and bcm.bedTypeMaster.recordStatus = 1 and bcm.wardMaster.recordStatus = 1 " +
            "and bcm.roomConfigurationMaster.recordStatus = 1")
    List<BedConfigurationMaster> getBedConfigurationMastersByClinicLocation(Long clinicLocationId);

    @Query("select bcm from BedConfigurationMaster bcm where bcm.wardMaster.id = ?1 " +
            "and bcm.bedTypeMaster.isDaycare = ?2 and bcm.recordStatus = 1 and bcm.bedTypeMaster.recordStatus = 1 " +
            "and bcm.wardMaster.recordStatus = 1 and bcm.roomConfigurationMaster.recordStatus = 1")
    List<BedConfigurationMaster> getBedConfigurationByWardAndDaycare(Long wardId, boolean isDaycare);

    @Query("select bcm from BedConfigurationMaster bcm where bcm.clinicLocationId = ?1 " +
            "and bcm.bedTypeMaster.isDaycare = ?2 and bcm.recordStatus = 1 and bcm.bedTypeMaster.recordStatus = 1 " +
            "and bcm.wardMaster.recordStatus = 1 and bcm.roomConfigurationMaster.recordStatus = 1 " +
            "and bcm.wardMaster.status = true")
    List<BedConfigurationMaster> getBedConfigurationByDaycare(Long clinicLocationId, boolean daycare);

    @Query("select bcm from BedConfigurationMaster bcm where bcm.clinicLocationId = ?1 " +
            "and bcm.bedNumber = ?2 and bcm.recordStatus = 1 and bcm.bedTypeMaster.id = ?3 and bcm.bedTypeMaster.recordStatus = 1 " +
            "and bcm.wardMaster.id = ?5 and bcm.wardMaster.recordStatus = 1 and bcm.roomConfigurationMaster.id = ?4 and bcm.roomConfigurationMaster.recordStatus = 1")
    BedConfigurationMaster checkDuplication(Long clinicLocationId, String bedNumber, Long bedTypeMasterId, Long roomMasterId, Long wardMasterId);
}
