package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;

/*
* created by raushan on 30-05-2018
* */

@Entity
@Table(name = "doctor_slot")
public class DoctorSlot extends BaseModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@Column(name="slot_status", nullable=false)
	private String slotStatus;

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


	public String getSlotStatus() {
		return slotStatus;
	}
	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}
}
