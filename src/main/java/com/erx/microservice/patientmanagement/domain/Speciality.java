package com.erx.microservice.patientmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "speciality")
public class Speciality extends BaseModel implements Serializable {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1567779487777930396L;

	@Column(name="code", nullable=true)
	private String code;
	
	@NotNull
    private String name;
	
	@Column(name="description", nullable=true)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
