package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import java.time.LocalDate;

/*
* created by raushan on 30-05-2018
* */

@Entity
@Table(name="schedule")
public class Schedule extends BaseModel{

	@OneToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "schedule_definition_id")
	private ScheduleDefinition scheduleDefinition;

	@Column(name = "schedule_date")
	private LocalDate scheduleDate;

	//Getters and setters

	public ScheduleDefinition getScheduleDefinition() {
		return scheduleDefinition;
	}

	public void setScheduleDefinition(ScheduleDefinition scheduleDefination) {
		this.scheduleDefinition = scheduleDefination;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
}
