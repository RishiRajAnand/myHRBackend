package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

import java.util.List;

public class SaveGroupDTO {

    private SaveIndividualDTO saveGroupInfoDTO;
    private List<GroupIndividualMedicineDTO> groupIndividualMedicineDTOs;

    public SaveIndividualDTO getSaveGroupInfoDTO() {
        return saveGroupInfoDTO;
    }

    public void setSaveGroupInfoDTO(SaveIndividualDTO saveGroupInfoDTO) {
        this.saveGroupInfoDTO = saveGroupInfoDTO;
    }

    public List<GroupIndividualMedicineDTO> getGroupIndividualMedicineDTOs() {
        return groupIndividualMedicineDTOs;
    }

    public void setGroupIndividualMedicineDTOs(List<GroupIndividualMedicineDTO> groupIndividualMedicineDTOs) {
        this.groupIndividualMedicineDTOs = groupIndividualMedicineDTOs;
    }
}
