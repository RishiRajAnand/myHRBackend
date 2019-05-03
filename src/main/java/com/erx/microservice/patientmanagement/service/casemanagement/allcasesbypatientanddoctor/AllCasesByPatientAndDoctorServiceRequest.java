package com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AllCasesRequestDTO;
import org.springframework.data.domain.Pageable;

public class AllCasesByPatientAndDoctorServiceRequest implements CommonServiceRequest {

    private AllCasesRequestDTO allCasesRequestDTO;
    private Pageable pageable;

    //constructor

    public AllCasesByPatientAndDoctorServiceRequest(AllCasesRequestDTO allCasesRequestDTO, Pageable pageable) {
        this.allCasesRequestDTO = allCasesRequestDTO;
        this.pageable = pageable;
    }

    public AllCasesByPatientAndDoctorServiceRequest() {
    }

    //getters and setters

    public AllCasesRequestDTO getAllCasesRequestDTO() {
        return allCasesRequestDTO;
    }

    public void setAllCasesRequestDTO(AllCasesRequestDTO allCasesRequestDTO) {
        this.allCasesRequestDTO = allCasesRequestDTO;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
