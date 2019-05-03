package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSlotRepository extends JpaRepository<DoctorSlot,Long> {
}
