package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.PatientAllDetails;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/*
 * created by Bahubali on 17-10-2018
 * */
@Repository
public interface PatientAllDetailsRepository extends JpaRepository<PatientAllDetails,Long> {

    @Transactional
    @Modifying
    @Query("update PatientAllDetails P set P.patientSalutation=?2,P.patientMobileNumber=?3, P.patientEmail=?4, " +
            " P.patientContactAddress=?5, P.patientTypeId=?6, P.patientTypeName=?7 where P.patientId=?1 ")
    void updatePatientDetails(Long patientId, String patientSalutation, String patientMobileNumber, String patientEmail,
                              String patientContactAddress, Long patientTypeId, String patientTypeName);

    @Transactional
    @Modifying
    @Query("update PatientAllDetails P set P.ipAdmissionNumber=?2,P.ipAdmissionId=?3, P.bedTypeName=?4, " +
            " P.bedTypeId=?5, P.bedNumber=?6, P.wardName=?7,P.roomNumber=?8, P.patientVisitTypeName=?9," +
            "  P.patientVisitTypeMasterId=?10,P.isDayCarePatient=?11,P.ipAdmissionOn=?12 where P.patientId=?1 ")
    int updateIPAdmissionDetails(Long patientId, String ipAdmissionNumber, Long ipAdmissionId, String bedTypeName,
                                  Long bedTypeId, String bedNumber, String wardName, String roomNumber,
                                  String patientVisitTypeName, Long patientVisitTypeMasterId, boolean dayCarePatient,
                                  LocalDateTime ipAdmissionOn);

    @Transactional
    @Modifying
    @Query("update PatientAllDetails P set P.patientMRN=?2 where P.patientId=?1 ")
    void updatePatientMRN(Long patientId, String patientMRN);

    @Transactional
    @Modifying
    @Query("update PatientAllDetails P set P.bedTypeName=?2,P.bedTypeId=?3, P.bedNumber=?4, P.wardName=?5, " +
            "P.roomNumber=?6 where P.patientId=?1 ")
    int updateBedTransferDetails(Long patientId, String bedTypeName, Long bedTypeId, String bedNumber, String wardName,
                                 String roomNumber);

    @Transactional
    @Modifying
    @Query("update PatientAllDetails P set P.patientMRN=?2,P.isPatientRegistered=?3 where P.patientId=?1 ")
    int updatePatientMRNAndIsRegisteredByPatientId(Long patientId, String patientMRN,boolean isRegistered);
}
