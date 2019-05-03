package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;


import java.util.List;

public class SaveMedicineDetailDTO {

    private Long therapyPlanningId;
    private List<IdQuantityDTO> idQuantityDTOs;
    private List<Long> medicineTypeIds;
    private String instruction;
    public Long getTherapyPlanningId() {
        return therapyPlanningId;
    }

    public void setTherapyPlanningId(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
    }

    public List<IdQuantityDTO> getIdQuantityDTOs() {
        return idQuantityDTOs;
    }

    public void setIdQuantityDTOs(List<IdQuantityDTO> idQuantityDTOs) {
        this.idQuantityDTOs = idQuantityDTOs;
    }

    public List<Long> getMedicineTypeIds() {
        return medicineTypeIds;
    }

    public void setMedicineTypeIds(List<Long> medicineTypeIds) {
        this.medicineTypeIds = medicineTypeIds;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
