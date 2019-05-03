package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
