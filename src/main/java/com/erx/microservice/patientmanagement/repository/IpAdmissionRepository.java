package com.erx.microservice.patientmanagement.repository;

/*
 * created by Latha on 29-11-2017
 * */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.Patient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IpAdmissionRepository extends JpaRepository<IpAdmission, Long> {

    @Query("select ip from IpAdmission ip where ip.bedMaster.id = ?1 and trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "and ip.recordStatus = 1 and ip.bedMaster.recordStatus = 1 and ip.bedMaster.bedTypeMaster.recordStatus = 1 " +
            "and ip.bedMaster.wardMaster.recordStatus = 1 and ip.bedMaster.roomConfigurationMaster.recordStatus = 1")
    List<IpAdmission> findActivePatient(Long bedConfigurationMasterId);

    @Query("select ip from IpAdmission ip where ip.bedMaster.wardMaster.id = ?1 and " +
            "ip.bedMaster.bedTypeMaster.isDaycare = ?2 and ip.userDetail.userStaff.id = ?3 " +
            "and trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "and ip.recordStatus = 1 and ip.bedMaster.recordStatus = 1 and ip.bedMaster.wardMaster.status = true " +
            "and ip.bedMaster.wardMaster.recordStatus=1 and ip.userDetail.userStaff.recordStatus=1 and " +
            "ip.bedMaster.bedTypeMaster.recordStatus=1")
    List<IpAdmission> getIpAdmissionByWardAndDaycareAndDoctor(Long wardId, boolean daycare, Long doctorId);

    @Query("select p, ip from Patient p inner join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "where p.clinicLocation.id = ?1 and p.patientAdditionalDetail.patientType IS not NULL " +
            "and p.recordStatus = 1 and ip.recordStatus = 1")
    List<Object[]> findByClinicLocation(Long clinicLocationId);

    @Query("select ip from Patient p inner join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "where p.id = ?1 and p.patientAdditionalDetail.patientType IS not NULL " +
            "and p.recordStatus = 1 and ip.recordStatus = 1")
    IpAdmission findByPatientId(Long patientId);

    @Query("select ip from IpAdmission ip inner join ip.patient p " +
            "with trim(upper(ip.ipAdmissionStatus)) = 'DISCHARGED' " +
            "where p.id = ?1 and p.clinic.id = ?2 and p.patientAdditionalDetail.patientType IS not NULL " +
            "and p.recordStatus = 1 and ip.recordStatus = 1")
    List<IpAdmission> findDischargedIpDetailsByPatient(Long patientId, Long clinicId);

    @Query("select ip from IpAdmission ip Join ip.patient p Join p.patientAdditionalDetail pa Join pa.patientType pt " +
            "where ip.ipAdmissionStatus = 'Admitted' and ip.clinic.id = ?1 and ip.recordStatus = 1 " +
            "and pt IS not NULL " +
            "and p.recordStatus = 1 and p.registered = TRUE " +
            " ORDER BY ip.admissionOn DESC")
    Page<IpAdmission> findByClinic(Long clinicId, Pageable pageable);

    Optional<IpAdmission> findFirstByPatientOrderByCreatedOnDesc(Patient patient);

    @Query("select ip from IpAdmission ip where ip.bedMaster.bedTypeMaster.isDaycare = ?1 and " +
            "ip.userDetail.userStaff.id = ?2 and trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "and ip.recordStatus = 1 and ip.bedMaster.recordStatus = 1 and ip.bedMaster.wardMaster.status = true" +
            " and ip.bedMaster.wardMaster.recordStatus=1 and ip.userDetail.userStaff.recordStatus=1 and " +
            "ip.bedMaster.bedTypeMaster.recordStatus=1")
    List<IpAdmission> getIpAdmissionByDaycareAndDoctor(boolean daycare, Long doctorId);

    @Query("select ip from IpAdmission ip where ip.ipAdmissionNumber = ?1 OR ip.dayCareAdmissionNumber =?1 and ip.recordStatus=1")
    IpAdmission getIpAdmissionByIpDcNumber(String ipDcAdmissionNumber);

    @Query("select ip.id from IpAdmission ip where ip.id= ?1 and ip.recordStatus=1")
    Long findById(Long ipAdmissionID);


    @Query("select IP.admissionOn from IpAdmission IP  where IP.id = ?1 and IP.createdOn>?2 and  IP.createdOn<?3 and IP.recordStatus = 1 ")
    LocalDateTime getAdmissionOnTimeById(Long ipAdmissionID, LocalDateTime lastCutOffTime, LocalDateTime cutOffTime);


    List<IpAdmission> findIpByPatientId(Long patientId);

    @Query("select ip.ipAdmissionNumber from IpAdmission ip where ip.id= ?1 ")
    String findIpAdmissionNumberById(Long ipAdmissionID);


    @Query("select btm.id,btm.bedTypeName from IpAdmission ip Join ip.patient p  Join ip.bedMaster bm " +
            "join bm.bedTypeMaster btm with trim(upper(ip.ipAdmissionStatus)) != 'DISCHARGED' " +
            "where p.id = ?1  and ip.recordStatus = 1")
    List<Object[]> findAllocatedBedIdAndNameByPatientId(Long patientId);

}
