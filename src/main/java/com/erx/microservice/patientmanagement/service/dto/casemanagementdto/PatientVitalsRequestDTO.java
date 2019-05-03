package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;


/**
 * Created by Latha on 16/08/18.
 */

public class PatientVitalsRequestDTO {

    private Long patientId;
    private Long bloodGroupId;
    private Long maritalStatusId;
    private Long occupationId;
    private String allergies;
    private VitalsDTO vitalsDTO;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Long bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Long getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Long occupationId) {
        this.occupationId = occupationId;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public VitalsDTO getVitalsDTO() {
        return vitalsDTO;
    }

    public void setVitalsDTO(VitalsDTO vitalsDTO) {
        this.vitalsDTO = vitalsDTO;
    }
}
