package com.erx.microservice.patientmanagement.repository;


import com.erx.microservice.patientmanagement.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
}
