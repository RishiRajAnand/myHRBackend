
package com.erx.microservice.patientmanagement.repository;


import com.erx.microservice.patientmanagement.domain.PackageCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by Raushan on 19/12/17.
 */
@Repository
public interface PackageCatalogueRepository extends JpaRepository<PackageCatalogue, Long> {

    @Query("select p from PackageCatalogue p where p.id = ?1")
    PackageCatalogue findOne(Long id);
}
