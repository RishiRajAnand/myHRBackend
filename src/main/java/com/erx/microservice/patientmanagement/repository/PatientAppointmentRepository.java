package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.PatientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* created by Latha on 05-01-18
* */

@Repository
public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Long> {

    @Query("select pa from PatientAppointment pa where pa.patient.id = ?1 and pa.recordStatus = 1")
    List<PatientAppointment> findAllAppointmentsByPatient(Long patientId);

    @Query("select pa from PatientAppointment pa Join pa.doctorSlot ds Join ds.schedule sc Join sc.scheduleDefinition scd " +
            "where pa.patient.id = ?1 and pa.recordStatus = 1 and scd.doctor.id = ?2 ORDER BY pa.createdOn DESC")
    List<PatientAppointment> findByPatientAndDoctor(Long patientId, Long doctorId);

    @Query("select pa.generatedPatId,u.firstName,u.lastName from PatientAppointment pa join pa.visitTypeMaster vtm " +
            "left join pa.doctorSlot ds left Join ds.schedule sc left Join sc.scheduleDefinition scd " +
            "join  scd.doctor d left join d.userStaff u where pa.id =?1 and vtm.visitType=?2 and pa.recordStatus =1")
    Object findByIdAndVisitType(Long patientAppointmentId, String visitType);
}
