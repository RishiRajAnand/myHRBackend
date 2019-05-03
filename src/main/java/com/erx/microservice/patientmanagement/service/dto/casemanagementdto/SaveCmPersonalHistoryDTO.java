package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 06/10/18.
 */

public class SaveCmPersonalHistoryDTO {

    private Long cmMasterId;
    private Long cmMasterDetailId;
    private Long  clinicId;
    private Long patientAppointmentId;
    private CmObservationCategoryDataDTO appetiteObservationDataDTO;
    private CmObservationCategoryDataDTO bladderObservationDataDTO;
    private CmObservationCategoryDataDTO sleepObservationDataDTO;
    private CmObservationCategoryDataDTO bowelObservationDataDTO;
    private CmObservationCategoryDataDTO dietObservationDataDTO;
    private CmObservationCategoryDataDTO habitObservationDataDTO;

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

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
