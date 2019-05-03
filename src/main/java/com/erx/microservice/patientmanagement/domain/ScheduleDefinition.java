package com.erx.microservice.patientmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
* created by raushan on 30-05-2018
* */

@Entity
@Table(name="schedule_definition")
public class ScheduleDefinition extends BaseModel {

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor_id")
	private UserDetail doctor;

	public UserDetail getDoctor() {
		return doctor;
	}

	public void setDoctor(UserDetail doctor) {
		this.doctor = doctor;
	}
}
