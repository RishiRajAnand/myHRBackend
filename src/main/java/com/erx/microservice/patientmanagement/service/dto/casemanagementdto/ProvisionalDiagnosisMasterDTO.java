package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 18/09/18.
 */

public class ProvisionalDiagnosisMasterDTO {

    private Long provisionalDiagnosisId;
    private String name;

    public Long getProvisionalDiagnosisId() {
        return provisionalDiagnosisId;
    }

    public void setProvisionalDiagnosisId(Long provisionalDiagnosisId) {
        this.provisionalDiagnosisId = provisionalDiagnosisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
