package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and p.mobileNumber = ?2 " +
            "and p.recordStatus = 1 and p.registered = TRUE and NOT p.patientIdExternal=null")
    Page<Object[]> getIpAdmissionByPhoneNumber(Long clinicId, String mobileNumber, Pageable pageable);


    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and p.patientIdExternal = ?2 " +
            "and p.recordStatus = 1 ")
    Page<Object[]> getIpAdmissionByMRN(Long clinicId, String mrn, Pageable pageable);


    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and ip.ipAdmissionNumber = ?2 " +
            "and p.recordStatus = 1 and NOT p.patientIdExternal=null")
    Page<Object[]> getIpAdmissionByIpAdmissionNumber(Long clinicId, String ipAdmissionNumber, Pageable pageable);


    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and ip.dayCareAdmissionNumber = ?2 " +
            "and p.recordStatus = 1 and NOT p.patientIdExternal=null")
    Page<Object[]> getIpAdmissionByDayCareAdmissionNumber(Long clinicId, String dayCareAdmissionNumber, Pageable pageable);

    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and p.registered = TRUE and NOT p.patientIdExternal=null and " +
            "p.recordStatus = 1 and p.patientName like %?2% and  p.clinic.id = ?1 and p.recordStatus = 1 ")
    Page<Object[]> getIpAdmissionByName(Long clinicId, String name, Pageable pageable);

    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 " +
            "and p.recordStatus = 1 and p.registered = TRUE and NOT p.patientIdExternal=null ")
    Page<Object[]> getIpAdmissionByClinic(Long clinicId, Pageable pageable);

    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where p.clinic.id = ?1 and p.patientRegisteredDate BETWEEN ?2 AND ?3 " +
            "and ip.admissionOn BETWEEN ?2 AND ?3 " +
            "and p.recordStatus = 1")
    List<Object[]> getIpAdmissionByDateRange(Long clinicId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.patientIdExternal = ?2 and p.recordStatus = 1")
    List<Patient> getPatientByMRN(Long clinicId, String searchValue);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.recordStatus = 1")
    List<Patient> getPatientByClinic(Long clinicId);

    @Query("select p, pr from Patient p  " +
            "left join p.patientRefunds pr with pr.recordStatus=1  " +
            "where p.recordStatus = 1  " +
            " and p.mobileNumber = ?1 and NOT p.patientIdExternal=null and p.clinicLocation.id = ?2 ORDER BY pr.updatedOn DESC")
    List<Object[]> findPatientAndPatientRefundByMobileNoAndClinicLocation(String mobileNo, Long clinicLocationId);

    @Query("select p, pr from Patient p  " +
            "left join p.patientRefunds pr with  pr.recordStatus=1 " +
            "where p.recordStatus = 1  " +
            " and p.patientIdExternal = ?1 and  p.clinicLocation.id = ?2 ORDER BY pr.updatedOn DESC")
    List<Object[]> findPatientAndPatientRefundByMRNAndClinicLocation(String MRN, Long clinicLocationId);

    @Query("select p, pr from Patient p  " +
            "left join p.patientRefunds pr with pr.recordStatus=1 " +
            "where p.recordStatus = 1  and NOT p.patientIdExternal=null " +
            " and  p.patientName like %?1% " +
            "and  p.clinicLocation.id = ?2  ORDER BY pr.updatedOn DESC")
    List<Object[]> findPatientAndPatientRefundByNameAndClinicLocation(String name, Long clinicLocationId);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.patientIdExternal = ?2 and p.recordStatus = 1")
    Patient getPatientByMRNAndClinic(Long clinicId, String mrn);

    //to search CRN Patients

    /*@Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 and p.mobileNumber = ?2 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber != null")
    List<Object[]> getCampPatientByPhoneNumber(Long clinicId, String mobileNumber);

    @Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 and p.patientIdExternal = ?2 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber != null")
    List<Object[]> getCampPatientByMRN(Long clinicId, String mrn);

    @Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 and p.campRegistrationNumber = ?2 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber != null")
    List<Object[]> getCampPatientByCRN(Long clinicId, String campRegistrationNumber);

    @Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 and p.clinicLocation.location.name = ?2 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber != null")
    List<Object[]> getCampPatientByLocation(Long clinicId, String campRegistrationNumber);

    @Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber != null")
    List<Object[]> getCampPatientByClinic(Long clinicId);

    @Query("select p, a from Patient p " +
            "left join p.addressInfo a " +
            "where p.clinic.id = ?1 and p.patientRegisteredDate BETWEEN ?2 AND ?3 " +
            "and p.recordStatus = 1 and p.campRegistrationNumber is not null")
    List<Object[]> getCampPatientByDateRange(Long clinicId, LocalDateTime startDate, LocalDateTime endDate);*/
    @Query("select p, a,ph, pcr from Patient p " +
            "left join p.addressInfo a " +
            "left join p.patientUhIdentifiers ph " +
            "inner join p.patientCampRegistrations pcr  " +
            "where p.clinic.id = ?1 and p.recordStatus = 1 and pcr.recordStatus=1")
    List<Object[]> getALLPatientDetailByClinic(Long clinicId);


    @Query("select p from Patient p where p.patientName = ?1  and " +
            "p.dateOfBirth = ?2 and p.gender = ?3 and p.mobileNumber = ?4 and p.recordStatus = 1")
    List<Patient> checkPatientDuplicacy(String patientName, LocalDate dob,
                                        String gender, String mobileNumber);

    @Query("select P.id from Patient P where P.id=?1 and P.recordStatus=1")
    Long findPatientByPatientId(Long patientId);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.patientName like %?2% and p.registered = TRUE and NOT p.patientIdExternal=null")
    Page<Patient> getPatientDetailsByName(Long clinicId, String searchValue, Pageable pageable);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.patientIdExternal = ?2 and p.recordStatus = 1")
    Page<Patient> getPatientDetailsByMRN(Long clinicId, String searchValue, Pageable pageable);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.mobileNumber = ?2 and p.registered = TRUE and NOT p.patientIdExternal=null")
    Page<Patient> getPatientDetailsByMobileNumber(Long clinicId, String searchValue, Pageable pageable);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.id = ?2 and p.recordStatus = 1")
    Page<Patient> getPatientDetailsByPatientId(Long clinicId, String searchValue, Pageable pageable);

    @Query("select p from Patient p where p.clinic.id = ?1 and p.recordStatus = 1 and p.registered = TRUE and NOT p.patientIdExternal=null")
    Page<Patient> getPatientDetailsByClinic(Long clinicId, String searchValue, Pageable pageable);

    @Query("select p, ip from Patient p " +
            "left join p.ipAdmission ip " +
            "with trim(upper(ip.ipAdmissionStatus)) <> 'DISCHARGED' " +
            "where ip.id = ?1 " +
            "and p.recordStatus = 1 and NOT p.patientIdExternal=null")
    List<Object[]> getIpAdmissionByIpAdmissionId(Long  ipAdmissionId);

    @Query("select P from Patient P where P.id=?1 and P.recordStatus=1")
    Patient findPatientById(Long patientId);

    @Query("select timestamp(P.patientRegisteredDate) from Patient P where P.id=?1 and P.recordStatus=1")
    Timestamp findRegistrationDateByPatientId(Long patientId);
}
