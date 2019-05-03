package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMasterTherapistDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyMasterTherapistDetailRepository extends JpaRepository<TherapyMasterTherapistDetail,Long>{

    @Query("select tmtd from TherapyMasterTherapistDetail tmtd where tmtd.therapyMaster.id=?1 and tmtd.recordStatus = 1")
    List<TherapyMasterTherapistDetail> findByTherapyMasterId(Long therapyMasterId);
}
