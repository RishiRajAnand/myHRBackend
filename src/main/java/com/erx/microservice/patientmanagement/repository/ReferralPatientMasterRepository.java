package com.erx.microservice.patientmanagement.repository;


import com.erx.microservice.patientmanagement.domain.ReferralPatientMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReferralPatientMasterRepository extends JpaRepository<ReferralPatientMaster,Long> {


    @Query("select R from ReferralPatientMaster R where R.recordStatus = 1 and R.clinicLocationId= ?1 ")
    List<ReferralPatientMaster> findByClinicLocationId(Long clinicLocationId);

    @Query("select R from ReferralPatientMaster R where R.recordStatus = 1 and R.clinicLocationId= ?1 and R.referralId= ?2 ")
    List<ReferralPatientMaster> findByReferralId(Long clinicLocationId, String referralId);


    @Query("select R from ReferralPatientMaster R where R.recordStatus = 1 and R.clinicLocationId= ?1 and R.referralName= ?2 ")
    List<ReferralPatientMaster> findByReferralName(Long clinicLocationId, String referralName);

    @Query("select R from ReferralPatientMaster R Join R.referralTypeLookupValue RT where R.recordStatus = 1 and R.clinicLocationId= ?1 and RT.name= ?2 ")
    List<ReferralPatientMaster> findByReferralType(Long clinicLocationId, String referralType);

    @Query("select R from ReferralPatientMaster R where R.recordStatus = 1 and R.clinicLocationId= ?1 and R.status= ?2 ")
    List<ReferralPatientMaster> findByStatus(Long clinicLocationId, String status);

    @Query("select R from ReferralPatientMaster R Join R.userDetail UD Join UD.userStaff US where R.recordStatus = 1 and R.clinicLocationId= ?1 and US.firstName LIKE %?2% or  US.lastName LIKE %?2% ")
    List<ReferralPatientMaster> findByDoctorName(Long clinicLocationId, String doctorName);


}

