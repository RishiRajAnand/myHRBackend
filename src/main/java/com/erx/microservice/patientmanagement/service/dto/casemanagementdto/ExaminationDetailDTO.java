package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

import java.time.LocalDateTime;
import java.util.List;

public class ExaminationDetailDTO {

    private Long detailId;
    private LocalDateTime examDate;
    private List<AllExaminationDTO> allExaminationDTOs;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public List<AllExaminationDTO> getAllExaminationDTOs() {
        return allExaminationDTOs;
    }

    public void setAllExaminationDTOs(List<AllExaminationDTO> allExaminationDTOs) {
        this.allExaminationDTOs = allExaminationDTOs;
    }
}
