package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund;

/*
* created by Raushan on 13-02-18
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.SearchPatientRefundDTO;

import java.util.List;

public class SearchPatientRefundServiceResponse extends CommonServiceResponse {

    private List<SearchPatientRefundDTO> searchPatientRefundDTOs;

    //Getters and setters


    public SearchPatientRefundServiceResponse() {
    }

    public SearchPatientRefundServiceResponse(List<SearchPatientRefundDTO> searchPatientRefundDTOs) {

        this.searchPatientRefundDTOs = searchPatientRefundDTOs;
    }

    //constructor

    public List<SearchPatientRefundDTO> getSearchPatientRefundDTOs() {
        return searchPatientRefundDTOs;
    }

    public void setSearchPatientRefundDTOs(List<SearchPatientRefundDTO> searchPatientRefundDTOs) {
        this.searchPatientRefundDTOs = searchPatientRefundDTOs;
    }
}
