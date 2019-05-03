package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Lookup;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.ws.WebEndpoint;
import java.util.List;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, Long> {

    List<LookupValue> findByLookupId(Lookup lookup);

    @Query("select LV from LookupValue LV  Join LV.lookupId L where L.name =?1 ")
    List<LookupValue> findByLookupName(String lookUpName);

    @Query("select LV from LookupValue LV  where LV.name =?1 ")
    LookupValue findLookUpByName(String salutation);

    @Query("select LV from LookupValue LV  where LV.name =?1 AND LV.lookupId =?2")
    LookupValue findLookUpByNameAndLookUpId(String lookUpName, Lookup lookup);

    LookupValue findById(Long statusTypeId);
}
