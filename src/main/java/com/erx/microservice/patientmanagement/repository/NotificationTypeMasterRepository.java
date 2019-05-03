package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.NotificationTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeMasterRepository extends JpaRepository<NotificationTypeMaster,Long> {
    @Query("select NTM from NotificationTypeMaster NTM  where NTM.notificationTypeName =?1 and recordStatus=1 ")
    NotificationTypeMaster findNotificationTypeMasterByNotificationTypeName(String notificationTypName);
}
