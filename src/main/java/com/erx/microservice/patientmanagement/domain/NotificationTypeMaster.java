package com.erx.microservice.patientmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notification_type_master")
public class NotificationTypeMaster extends BaseModel{

    @Column(name = "notification_type_code", nullable = false)
    private String notificationTypeCode;

    @Column(name = "notification_type_name", nullable = false)
    private String notificationTypeName;

    @Column(name = "description", nullable = false)
    private String description;

    public String getNotificationTypeCode() {
        return notificationTypeCode;
    }

    public void setNotificationTypeCode(String notificationTypeCode) {
        this.notificationTypeCode = notificationTypeCode;
    }

    public String getNotificationTypeName() {
        return notificationTypeName;
    }

    public void setNotificationTypeName(String notificationTypeName) {
        this.notificationTypeName = notificationTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
