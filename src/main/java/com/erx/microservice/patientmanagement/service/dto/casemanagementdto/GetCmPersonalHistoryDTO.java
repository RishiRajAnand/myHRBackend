package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 06/10/18.
 */

public class GetCmPersonalHistoryDTO {

    private CmObservationCategoryDataDTO appetiteObservationDataDTO;
    private CmObservationCategoryDataDTO bladderObservationDataDTO;
    private CmObservationCategoryDataDTO sleepObservationDataDTO;
    private CmObservationCategoryDataDTO bowelObservationDataDTO;
    private CmObservationCategoryDataDTO dietObservationDataDTO;
    private CmObservationCategoryDataDTO habitObservationDataDTO;

    public CmObservationCategoryDataDTO getAppetiteObservationDataDTO() {
        return appetiteObservationDataDTO;
    }

    public void setAppetiteObservationDataDTO(CmObservationCategoryDataDTO appetiteObservationDataDTO) {
        this.appetiteObservationDataDTO = appetiteObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getBladderObservationDataDTO() {
        return bladderObservationDataDTO;
    }

    public void setBladderObservationDataDTO(CmObservationCategoryDataDTO bladderObservationDataDTO) {
        this.bladderObservationDataDTO = bladderObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getSleepObservationDataDTO() {
        return sleepObservationDataDTO;
    }

    public void setSleepObservationDataDTO(CmObservationCategoryDataDTO sleepObservationDataDTO) {
        this.sleepObservationDataDTO = sleepObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getBowelObservationDataDTO() {
        return bowelObservationDataDTO;
    }

    public void setBowelObservationDataDTO(CmObservationCategoryDataDTO bowelObservationDataDTO) {
        this.bowelObservationDataDTO = bowelObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getDietObservationDataDTO() {
        return dietObservationDataDTO;
    }

    public void setDietObservationDataDTO(CmObservationCategoryDataDTO dietObservationDataDTO) {
        this.dietObservationDataDTO = dietObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getHabitObservationDataDTO() {
        return habitObservationDataDTO;
    }

    public void setHabitObservationDataDTO(CmObservationCategoryDataDTO habitObservationDataDTO) {
        this.habitObservationDataDTO = habitObservationDataDTO;
    }
}
