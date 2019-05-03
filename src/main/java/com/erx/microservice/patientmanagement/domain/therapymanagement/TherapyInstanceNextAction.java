package com.erx.microservice.patientmanagement.domain.therapymanagement;
/*
* created by raushan on 28-08-2018
* */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.erx.microservice.patientmanagement.domain.BaseModel;


@Entity
@Table(name = "tm_therapy_instance_next_action")

public class TherapyInstanceNextAction extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "tm_therapy_instance_id")
	private TherapyInstance therapyInstance;

	@Column(name = "next_action")
	private String nextAction;

	public TherapyInstance getTherapyInstance() {
		return therapyInstance;
	}

	public void setTherapyInstance(TherapyInstance therapyInstance) {
		this.therapyInstance = therapyInstance;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
}